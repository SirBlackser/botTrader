package be.ds.projects.botTrader.model.repository;

import be.ds.projects.botTrader.model.Ask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Steven de Cleene
 */
@Repository
public interface AskRepository extends JpaRepository<Ask, Long> {

    @Query("SELECT a FROM Ask a WHERE order_book_id = :ob_id")
    List<Ask> getAsksFromOrderbookId(@Param("ob_id") final Integer orderBookId);

}
