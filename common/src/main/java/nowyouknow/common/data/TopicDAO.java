package nowyouknow.common.data;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface TopicDAO extends CrudRepository<Topic, Long> {
	public Topic findByName(String name);
}
