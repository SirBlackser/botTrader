package be.ds.projects.botTrader.quartz.jobs;

import be.ds.projects.botTrader.bitstamp.BitStampInterfacer;
import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.DataPoint;
import be.ds.projects.botTrader.model.OrderBook;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.model.repository.DataCollectionRepository;
import be.ds.projects.botTrader.model.repository.DataPointRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Quartz job for the collection of data for a specific currency over a specified period of time.
 * For Job configuration / setup, see DataCollectionSetup.
 *
 * @author Steven de Cleene
 */
public class DataCollectionJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataCollectionJob.class);

    private static final int CONSECUTIVE_FAILURE_THRESHOLD = 3;

    private static int failureCount = 0;

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        try {
            final String dataCollectionIdentifier = jobExecutionContext.getJobDetail().getKey().getName().substring(2);
            final DataCollection dataCollection = (DataCollection) jobExecutionContext.getScheduler().getContext().get(dataCollectionIdentifier);
            final ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) jobExecutionContext.getScheduler().getContext().get("springCtx");
            final Ticker ticker = BitStampInterfacer.ticker(dataCollection.getCurrencyPair());
            final OrderBook orderBook = BitStampInterfacer.orderBook(dataCollection.getCurrencyPair());
            final DataPoint dataPoint = new DataPoint(ticker, orderBook, dataCollection);
            final DataPointRepository dataPointRepository = ctx.getBean("dataPointRepository", DataPointRepository.class);
            dataPointRepository.save(dataPoint);
            failureCount = 0;
        } catch (SchedulerException e) {
            LOGGER.error("Unable to access Spring's ApplicationContext -> system exit", e);
            System.exit(-1);
        } catch (Exception e) {
            failureCount++;
            if (failureCount == CONSECUTIVE_FAILURE_THRESHOLD) {
                nonGracefulExit(jobExecutionContext);
            }
        }
    }

    /**
     * Method that gets called when there's a problem to connect with BitStamp.
     * After 3 consecutive failures set the DataCollection's gracefulFinish to false, save and quit.
     *
     * TODO: TEST
     *
     * @param jobExecutionContext the DataCollection's context to fetch settings (Spring, repositories)
     */
    private void nonGracefulExit(final JobExecutionContext jobExecutionContext) {
        try {
            final ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) jobExecutionContext.getScheduler().getContext().get("springCtx");
            final String dataCollectionIdentifier = jobExecutionContext.getJobDetail().getKey().getName().substring(2);
            final DataCollection dataCollection = (DataCollection) jobExecutionContext.getScheduler().getContext().get(dataCollectionIdentifier);
            final DataCollectionRepository dataCollectionRepository = ctx.getBean("dataCollectionRepository", DataCollectionRepository.class);
            dataCollection.setGracefulFinish(false);
            dataCollectionRepository.save(dataCollection);
            LOGGER.error(CONSECUTIVE_FAILURE_THRESHOLD + " missed calls to the BitStamp API, exiting ungracefully.");
            System.exit(-1);
        } catch (SchedulerException e) {
            LOGGER.error("Unable to access Spring's ApplicationContext -> system exit", e);
            System.exit(-1);
        }
    }

}
