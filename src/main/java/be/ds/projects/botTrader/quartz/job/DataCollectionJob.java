package be.ds.projects.botTrader.quartz.job;

import be.ds.projects.botTrader.model.DataCollection;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

/**
 * @author Steven de Cleene
 */
public class DataCollectionJob implements InterruptableJob {

    private String jobName;

    private String jobGroup;

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        try {
            final DataCollection dataCollection = (DataCollection) jobExecutionContext.getScheduler().getContext().get("DataCollection");
            System.out.println(dataCollection.getCurrencyPair());
            System.out.println(dataCollection.getStopTimeStamp());
            jobExecutionContext.getScheduler().interrupt(new JobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            // ignore
        }
    }

    @Override
    public void interrupt() {
        Thread.currentThread().interrupt();
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

}
