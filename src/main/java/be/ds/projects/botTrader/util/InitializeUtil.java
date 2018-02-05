package be.ds.projects.botTrader.util;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.OrderBook;
import be.ds.projects.botTrader.model.repository.AskRepository;
import be.ds.projects.botTrader.model.repository.BidRepository;
import be.ds.projects.botTrader.model.repository.DataPointRepository;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Steven de Cleene
 */
public class InitializeUtil {

    public static DataCollection initialize(final ConfigurableApplicationContext ctx, final DataCollection dataCollection) {
        dataCollection.setDataPoints(ctx.getBean("dataPointRepository", DataPointRepository.class).getDataPointsForDataCollectionId(dataCollection.getId()));
        return dataCollection;
    }

    public static OrderBook initialize(final ConfigurableApplicationContext ctx, final OrderBook orderBook) {
        orderBook.setAsks(ctx.getBean("askRepository", AskRepository.class).getAsksFromOrderbookId(orderBook.getId()));
        orderBook.setBids(ctx.getBean("bidRepository", BidRepository.class).getBidsFromOrderbookId(orderBook.getId()));
        return orderBook;
    }

}
