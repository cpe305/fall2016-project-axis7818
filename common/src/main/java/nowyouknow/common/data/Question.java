package nowyouknow.common.data;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String text;
	public Boolean open;
	public Date whenAsked;
	public Topic topic;
	public Reaction reaction;
	
	public Question() { }
	
	@Override
	public String toString() {
		return String.format("<Question: id=%d>", this.id);
	}
}
