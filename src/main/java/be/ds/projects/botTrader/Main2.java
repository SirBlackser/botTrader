package be.ds.projects.botTrader;

import be.ds.projects.botTrader.model.repository.DataCollectionRepository;
import be.ds.projects.botTrader.testbench.algorithm.AlgorithmS01;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static be.ds.projects.botTrader.util.JpaInitializeUtil.initialize;

/**
 * @author Steven de Cleene
 */
@SpringBootApplication
public class Main2 {

    /*
        Let op de initialize call op lijn 23, deze komt van een static import die lazy loading gaat doen.

        Standaard gaan datapoints van een datacollection, en asks en bids van een orderbook niet in de objecten steken.
        Via initialize(datacollection) of initialize(orderbook) gaan deze erin gelaad worden.

        Check JpaInitializeUtil voor meer info.
     */
    public static void main(String... args) throws Exception {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Main2.class, args);
        final DataCollectionRepository dataCollectionRepository = ctx.getBean("dataCollectionRepository", DataCollectionRepository.class);
        new AlgorithmS01(initialize(ctx, dataCollectionRepository.findDataCollectionById(1))).execute();
    }

}
