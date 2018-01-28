package be.ds.projects.botTrader;

import be.ds.projects.botTrader.quartz.QuartzScheduler;
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
