package be.ds.projects.botTrader;

import be.ds.projects.botTrader.bitstamp.BitStampInterfacer;
import be.ds.projects.botTrader.model.CurrencyPair;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.model.TickerRepository;
import be.ds.projects.botTrader.quartz.QuartzScheduler;
import org.quartz.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Steven de Cleene
 */
@SpringBootApplication
public class Main {

    public static void main(String... args) throws Exception {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
        final QuartzScheduler scheduler = new QuartzScheduler(ctx);
        scheduler.initializeJobs();
    }

}
