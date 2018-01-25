package be.ds.projects.botTrader.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Steven de Cleene
 */
@Repository("tickerRepository")
public interface TickerRepository extends CrudRepository<Ticker, Long> {}
