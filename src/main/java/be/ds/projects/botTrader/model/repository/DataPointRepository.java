package be.ds.projects.botTrader.model.repository;

import be.ds.projects.botTrader.model.DataPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Steven de Cleene
 */
@Repository
public interface DataPointRepository extends JpaRepository<DataPoint, Long> { }