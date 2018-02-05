package be.ds.projects.botTrader.model.repository;

import be.ds.projects.botTrader.model.DataCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Steven de Cleene
 */
@Repository
public interface DataCollectionRepository extends JpaRepository<DataCollection, Long> {

    /**
     * Gets a DataCollection based on an ID.
     *
     * @param id ID to get a DataCollection from
     * @return DataCollection with the specified ID.
     */
    DataCollection findDataCollectionById(final Integer id);

}
