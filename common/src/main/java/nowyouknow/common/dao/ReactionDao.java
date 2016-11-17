package nowyouknow.common.dao;

import nowyouknow.common.data.Reaction;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Provides basic CRUD operations to Reaction data.
 */
@Transactional
public interface ReactionDao extends CrudRepository<Reaction, Long> {

}
