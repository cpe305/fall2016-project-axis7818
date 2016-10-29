package nowyouknow.common.data;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TopicDao extends CrudRepository<Topic, Long> {
  public Topic findByName(String name);
}
