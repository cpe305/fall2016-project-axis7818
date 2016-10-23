package nowyouknow.common.data;

import java.io.Serializable;

public class Reaction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Long id;
	public Integer likes;
	public Integer dislikes;
	public Integer laughs;

	public Reaction() { 
		this.likes = 0;
		this.dislikes = 0;
		this.laughs = 0;
	}
	
	public Integer like() {
		return ++this.likes;
	}
	
	public Integer dislike() {
		return ++this.dislikes;
	}
	
	public Integer laugh() {
		return ++this.laughs;
	}
	
	@Override
	public String toString() {
		return String.format("<Reaction: id=%d>", this.id);
	}
}
