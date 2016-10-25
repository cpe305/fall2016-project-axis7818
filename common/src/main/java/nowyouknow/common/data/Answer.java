package nowyouknow.common.data;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String text;
	public Date whenAnswered;
	public Question question;
	public Reaction reaction;

	protected Answer() { }
	
	public Answer(Question question, String text) {
		this.question = question;
		this.text = text;
		this.whenAnswered = new Date();
		this.reaction = new Reaction();
	}
	
	@Override
	public String toString() {
		return String.format("<Answer id=%d>", this.id);
	}
}
