package be.ds.projects.botTrader.model.repository;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Steven de Cleene
 */
@Repository
public interface OrderBookRepository extends JpaRepository<DataCollection, Long> {

    @Query("SELECT ob FROM OrderBook ob JOIN FETCH ob.asks WHERE ob = ?1")
    OrderBook initializeAsks(final OrderBook orderBook);

    @Query("SELECT ob FROM OrderBook ob JOIN FETCH ob.bids WHERE ob = ?1")
    OrderBook initializeBids(final OrderBook orderBook);

}
