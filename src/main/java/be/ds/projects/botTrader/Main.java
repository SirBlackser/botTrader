package be.ds.projects.botTrader;

import be.ds.projects.botTrader.model.CurrencyPair;
import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.repository.DataCollectionRepository;
import be.ds.projects.botTrader.quartz.QuartzScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

import static be.ds.projects.botTrader.util.DateTimeUtil.localDateTimeToUnixTimestamp;

/**
 * @author Steven de Cleene
 */
@SpringBootApplication
public class Main {

    public static void main(String... args) throws Exception {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        final Long startTimestamp = localDateTimeToUnixTimestamp(LocalDateTime.now());
        final Long stopTimestamp = localDateTimeToUnixTimestamp(LocalDateTime.now().plusHours(1));

        final DataCollection dataCollection = new DataCollection(CurrencyPair.BTC2EUR, startTimestamp, stopTimestamp, 1);
        final DataCollectionRepository dataCollectionRepository = ctx.getBean("dataCollectionRepository", DataCollectionRepository.class);
        dataCollectionRepository.save(dataCollection);

        final QuartzScheduler scheduler = new QuartzScheduler(ctx);
        scheduler.addDataCollectionJob(dataCollection);

        while(true) {
            Thread.sleep(500);
            if (!scheduler.hasRunningJobs()) {
                break;
            }
        }
        scheduler.shutDown();
    }

}
