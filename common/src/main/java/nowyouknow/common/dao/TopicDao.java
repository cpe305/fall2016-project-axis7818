package nowyouknow.common.dao;

import nowyouknow.common.data.Topic;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Provides basic CRUD operations to Topic data.
 */
@Transactional
public interface TopicDao extends CrudRepository<Topic, Long> {
  
  /**
   * Get a topic by name.
   * @param name the topic's name.
   * @return the topic.
   */
  public Topic findByName(String name);
}
