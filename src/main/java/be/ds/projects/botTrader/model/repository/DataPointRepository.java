package be.ds.projects.botTrader.model.repository;

import be.ds.projects.botTrader.model.DataPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Steven de Cleene
 */
@Repository
public interface DataPointRepository extends JpaRepository<DataPoint, Long> {

    /**
     * Gets all DataPoints for a specific DataCollection. Implemented for lazy loading (see JpaInitializeUtil).
     *
     * @param dataCollectionId DataCollection ID to get the DataPoints from
     * @return List of DataPoints linked to the specified DataCollection
     */
    @Query("SELECT dp FROM DataPoint dp WHERE data_collection_id = :dc_id")
    List<DataPoint> getDataPointsForDataCollectionId(@Param("dc_id") final Integer dataCollectionId);

}
