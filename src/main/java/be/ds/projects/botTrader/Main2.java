package be.ds.projects.botTrader;

import be.ds.projects.botTrader.bitstamp.BitStampInterfacer;
import be.ds.projects.botTrader.model.*;
import be.ds.projects.botTrader.model.repository.DataCollectionRepository;
import be.ds.projects.botTrader.model.repository.DataPointRepository;
import be.ds.projects.botTrader.util.DateTimeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

/**
 * @author Steven de Cleene
 */
@SpringBootApplication
public class Main2 {

    public static void main(String... args) throws Exception {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Main2.class, args);

        final OrderBook orderBook1 = BitStampInterfacer.orderBook(CurrencyPair.BTC2EUR);
        final OrderBook orderBook2 = BitStampInterfacer.orderBook(CurrencyPair.BTC2EUR);
        final Ticker ticker1 = BitStampInterfacer.ticker(CurrencyPair.BTC2EUR);
        final Ticker ticker2 = BitStampInterfacer.ticker(CurrencyPair.BTC2EUR);

        final DataCollectionRepository dataCollectionRepository = ctx.getBean("dataCollectionRepository", DataCollectionRepository.class);
        final DataCollection dataCollection = new DataCollection(
                CurrencyPair.BTC2EUR,
                DateTimeUtil.localDateTimeToUnixTimestamp(LocalDateTime.now()),
                DateTimeUtil.localDateTimeToUnixTimestamp(LocalDateTime.now().plusDays(3)),
                5);
        dataCollectionRepository.save(dataCollection);

        final DataPointRepository dataPointRepository = ctx.getBean("dataPointRepository", DataPointRepository.class);
        final DataPoint dataPoint1 = new DataPoint(ticker1, orderBook1);
        final DataPoint dataPoint2 = new DataPoint(ticker2, orderBook2);
        dataPoint1.setDataCollection(dataCollection);
        dataPoint2.setDataCollection(dataCollection);
        dataPointRepository.save(dataPoint1);
        dataPointRepository.save(dataPoint2);
    }

}
