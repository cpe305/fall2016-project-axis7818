package nowyouknow.common.dao;

import nowyouknow.common.data.Topic;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Provides basic CRUD operations to Topic data.
 */
@Transactional
public interface TopicDao extends CrudRepository<Topic, Long> {
  public Topic findByName(String name);
}
