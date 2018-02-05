package be.ds.projects.botTrader.quartz;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.quartz.jobs.DataCollectionSetup;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Implementation of a custom Quartz scheduler to run our defined job(s).
 *
 * @author Steven de Cleene
 */
public class QuartzScheduler {

    private Scheduler scheduler;

    public QuartzScheduler(final ConfigurableApplicationContext ctx) throws Exception {
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.getContext().put("springCtx", ctx);
        scheduler.start();
    }

    public Boolean hasRunningJobs() throws Exception {
        return scheduler.getJobGroupNames().size() > 0;
    }

    public void shutDown() throws Exception {
        scheduler.shutdown();
    }

    public void addDataCollectionJob(final DataCollection dataCollection) throws SchedulerException {
        final String jobIdentifier = "dataCollection_" + dataCollection.getCurrencyPair().currencyPair() + "_" +
                dataCollection.getReadInterval() + "_" + dataCollection.getStopTimeStamp();
        final DataCollectionSetup dataCollectionSetup = new DataCollectionSetup(jobIdentifier, "dataCollection");
        scheduler.getContext().put(jobIdentifier, dataCollection);
        scheduler.scheduleJob(dataCollectionSetup.getJobDetail(), dataCollectionSetup.getTrigger(dataCollection));
    }

}
