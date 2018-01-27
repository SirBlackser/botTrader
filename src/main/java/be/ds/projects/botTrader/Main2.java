package be.ds.projects.botTrader;

import be.ds.projects.botTrader.bitstamp.BitStampInterfacer;
import be.ds.projects.botTrader.model.CurrencyPair;
import be.ds.projects.botTrader.model.OrderBook;
import be.ds.projects.botTrader.model.OrderBookRepository;
import be.ds.projects.botTrader.quartz.QuartzScheduler;
import be.ds.projects.botTrader.util.ToStringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Steven de Cleene
 */
@SpringBootApplication
public class Main2 {

    public static void main(String... args) throws Exception {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Main2.class, args);

        final OrderBookRepository orderBookRepository = ctx.getBean("orderBookRepository", OrderBookRepository.class);
        final OrderBook orderBook = BitStampInterfacer.orderBook(CurrencyPair.BTC2EUR);
        orderBookRepository.save(orderBook);
//        System.out.println(ToStringUtil.prettyPrintJson(orderBook));

    }

}
