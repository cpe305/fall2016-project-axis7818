package nowyouknow.common.data;

import java.util.List;

public interface AnswerDAO {
	// Create
	public void save(Answer answer);
	
	// Read
	public List<Answer> list(Question question);
	public List<Answer> list(Question question, int count);
	public Answer get(Long id);
	
	// Update
	public void update(Answer answer);
	
	// Delete
	public void delete(Answer answer);
	public void delete(Long id);
}
