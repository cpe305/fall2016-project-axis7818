package nowyouknow.common.dao;

import java.util.List;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;

public interface AnswerDAO {
	// Create
	public void save(Answer answer);
	
	// Read
	public List<Answer> get(Question question);
	public List<Answer> get(Question question, int count);
	public Answer get(long id);
	
	// Update
	public void update(Answer answer);
	
	// Delete
	public void delete(Answer answer);
	public void delete(long id);
}
