package nowyouknow.common.data;

import java.io.Serializable;

public class Reaction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Long id;
	public Integer likes;
	public Integer dislikes;
	public Integer laughs;

	public Reaction() { }
	
	@Override
	public String toString() {
		return String.format("<Reaction: id=%d>", this.id);
	}
}
