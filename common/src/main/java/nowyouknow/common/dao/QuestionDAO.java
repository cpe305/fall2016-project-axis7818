package nowyouknow.common.dao;

import java.util.List;

import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;

public interface QuestionDAO {
	// Create 
	public void save(Question question);
	
	// Read
	public List<Question> get(Topic topic);
	public List<Question> get(Topic topic, int count);
	public Question get(long id);
	
	// Update
	public void update(Question question);
	
	// Delete
	public void delete(Question question);
	public void delete(long id);
}
