package be.ds.projects.botTrader.quartz;

import be.ds.projects.botTrader.quartz.job.ReadTickerJob;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import static be.ds.projects.botTrader.quartz.QuartzJobCronStrings.*;

/**
 * @author Steven de Cleene
 */
public class QuartzScheduler {

    private final Scheduler scheduler;

    public QuartzScheduler(final ConfigurableApplicationContext ctx) throws Exception {
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.getContext().put("springCTX", ctx);
    }

    public void initializeJobs() throws Exception {
        final JobDetail readTickerJob = buildJob(ReadTickerJob.class);
        final Trigger readTickerTrigger = buildTrigger(ReadTickerJob.class, CRON_STRING_READ_TICKER);
        scheduler.scheduleJob(readTickerJob, readTickerTrigger);
    }

    private JobDetail buildJob(final Class clazz) {
        return JobBuilder.newJob(clazz)
                .withIdentity(clazz.getName(), "trader")
                .build();
    }

    private Trigger buildTrigger(final Class clazz, final String cronString) {
        return TriggerBuilder.newTrigger()
                .withIdentity(clazz.getName() + "_trigger", "trader")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronString))
                .build();
    }

}
