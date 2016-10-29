package nowyouknow.common.dao;

import nowyouknow.common.data.Reaction;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ReactionDao extends CrudRepository<Reaction, Long> {

}
