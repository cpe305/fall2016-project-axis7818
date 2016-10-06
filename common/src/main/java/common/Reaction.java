package common;

public class Reaction {
	private long id;
	private int likes;
	private int dislikes;
	private int laughs;
	
	public Reaction() {
		this(0, 0, 0);
	}
	
	public Reaction(int likes, int dislikes, int laughs) {
		this.likes = likes;
		this.dislikes = dislikes;
		this.laughs = laughs;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public int getDislikes() {
		return dislikes;
	}
	
	public int getLaughs() {
		return laughs;
	}
	
	public int netLikes() {
		return likes - dislikes;
	}
	
	public void Like() {
		++likes;
	}
	
	public void Dislike() {
		++dislikes;
	}
	
	public void Laugh() {
		++laughs;
	}
}
