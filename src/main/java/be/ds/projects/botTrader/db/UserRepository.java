package be.ds.projects.botTrader.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Steven de Cleene
 */
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {}
