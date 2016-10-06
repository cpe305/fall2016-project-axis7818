package common;

import java.util.Date;

public class Answer {
	private long id;
	private Question question;
	private Reaction reaction;
	private String text;
	private Date whenAnswered;
	
	public Answer() { }
	
	public Answer(Question question, String text) {
		this.question = question;
		this.text = text;
		this.reaction = new Reaction();
		this.whenAnswered = new Date();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getWhenAnswered() {
		return whenAnswered;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public Reaction getReaction() {
		return reaction;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", question.toString(), text);
	}
}
