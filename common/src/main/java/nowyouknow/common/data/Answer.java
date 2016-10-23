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

	public Answer() { }
	
	@Override
	public String toString() {
		return String.format("<Answer id=%d>", this.id);
	}
}
