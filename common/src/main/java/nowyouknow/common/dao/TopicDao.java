package nowyouknow.common.dao;

import nowyouknow.common.data.Topic;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TopicDao extends CrudRepository<Topic, Long> {
  public Topic findByName(String name); // TODO: remove this and manage topics by id
}
