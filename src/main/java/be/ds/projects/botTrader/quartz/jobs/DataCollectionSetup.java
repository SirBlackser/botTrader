package be.ds.projects.botTrader.quartz.jobs;

import be.ds.projects.botTrader.model.DataCollection;
import org.quartz.*;

import static be.ds.projects.botTrader.util.DateTimeUtil.unixTimestampToDate;

/**
 * Configuration for the DataCollection Quartz Job.
 *
 * @author Steven de Cleene
 */
public class DataCollectionSetup {

    private String jobName;

    private String groupName;

    public DataCollectionSetup(final String jobName, final String groupName) {
        this.jobName = jobName;
        this.groupName = groupName;
    }

    public JobDetail getJobDetail() {
        return JobBuilder.newJob(DataCollectionJob.class)
                .withIdentity("j_" + jobName, groupName)
                .build();
    }

    /**
     * Gets a trigger for the DataCollectionJob based on the configuration provided in the specified DataCollection.
     *
     * @param dataCollection DataCollection to access the read interval and stop time from
     * @return Trigger object for the DataCollectionJob
     */
    public Trigger getTrigger(final DataCollection dataCollection) {
        return TriggerBuilder.newTrigger()
                .withIdentity("t_" + jobName, "dataCollection")
                .startNow()
                .withSchedule(getSchedule(dataCollection.getReadInterval()))
                .endAt(unixTimestampToDate(dataCollection.getStopTimeStamp()))
                .build();
    }

    private SimpleScheduleBuilder getSchedule(final Integer interval) {
        return SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(interval)
                .repeatForever();
    }

}
