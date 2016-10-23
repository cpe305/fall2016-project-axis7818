package nowyouknow.common.data;

import java.util.List;

public interface TopicDAO {
	// Create
	public void save(Topic topic);
	
	// Read
	public List<Topic> list();
	public Topic get(Long id);
	public Topic get(String name);
	
	// Update
	public void update(Topic topic);
	
	// Delete
	public void delete(Long id);
	public void delete(String name);
}
