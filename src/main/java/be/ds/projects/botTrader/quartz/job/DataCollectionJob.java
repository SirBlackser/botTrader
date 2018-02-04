package be.ds.projects.botTrader.quartz.job;

import be.ds.projects.botTrader.bitstamp.BitStampInterfacer;
import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.DataPoint;
import be.ds.projects.botTrader.model.OrderBook;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.model.repository.DataPointRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Steven de Cleene
 */
public class DataCollectionJob implements Job {

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
        } catch (Exception e) {
            // ignore
        }
    }

}
