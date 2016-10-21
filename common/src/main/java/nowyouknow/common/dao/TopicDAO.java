package nowyouknow.common.dao;

import java.util.List;

import nowyouknow.common.data.Topic;

public interface TopicDAO {
	// Create
	public void save(Topic topic);
	
	// Read
	public List<Topic> list();
	public Topic get(long id);
	public Topic get(String name);
	
	// Update
	public void update(Topic topic);
	
	// Delete
	public void delete(long id);
	public void delete(String name);
}
