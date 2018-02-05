package be.ds.projects.botTrader.model.repository;

import be.ds.projects.botTrader.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Steven de Cleene
 */
@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("SELECT b FROM Bid b WHERE order_book_id = :ob_id")
    List<Bid> getBidsFromOrderbookId(@Param("ob_id") final Integer orderBookId);

}
