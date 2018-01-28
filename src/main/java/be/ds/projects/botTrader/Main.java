package be.ds.projects.botTrader;

import be.ds.projects.botTrader.model.CurrencyPair;
import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.repository.DataCollectionRepository;
import be.ds.projects.botTrader.quartz.job.DataCollectionJob;
import be.ds.projects.botTrader.util.DateTimeUtil;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Steven de Cleene
 */
@SpringBootApplication
public class Main {

    public static void main(String... args) throws Exception {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        final Long unixTimestampStart = DateTimeUtil.localDateTimeToUnixTimestamp(LocalDateTime.now());
        final Long unixTimestampStop = DateTimeUtil.localDateTimeToUnixTimestamp(LocalDateTime.now().plusDays(3));

        final DataCollection dataCollection = new DataCollection(CurrencyPair.BTC2EUR, unixTimestampStart, unixTimestampStop, 5);
        final DataCollectionRepository dataCollectionRepository = ctx.getBean("dataCollectionRepository", DataCollectionRepository.class);
        dataCollectionRepository.save(dataCollection);

        final JobDetail job = newJob(DataCollectionJob.class)
                .withIdentity("dataCollectionJob", "group1")
                .usingJobData("jobName", "dataCollectionJob")
                .usingJobData("jobGroup", "group1")
                .build();

        final Trigger trigger = newTrigger()
                .withIdentity("dataCollectionTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(3)
                    .repeatForever())
                .build();


        final Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.getContext().put("DataCollection", dataCollection);
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

}
