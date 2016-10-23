package nowyouknow.common.data;

import java.util.List;

public interface QuestionDAO {
	// Create 
	public void save(Question question);
	
	// Read
	public List<Question> list(Topic topic);
	public List<Question> list(Topic topic, int count);
	public Question get(Long id);
	
	// Update
	public void update(Question question);
	
	// Delete
	public void delete(Question question);
	public void delete(Long id);
}
