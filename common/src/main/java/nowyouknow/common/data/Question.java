package nowyouknow.common.data;

import java.util.Date;

public class Question implements Reactable {
	private long id;
	private Topic topic;
	private String text;
	private Reaction reaction;
	private boolean open;
	private Date whenAsked;
	
	public Question() { }
	
	public Question(String text) {
		this(text, null);
	}
	
	public Question(String text, Topic topic) {
		this.topic = topic;
		this.text = text;
		this.reaction = new Reaction();
		this.open = true;
		this.whenAsked = new Date();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getWhenAsked() {
		return whenAsked;
	}
	
	public Topic getTopic() {
		return topic;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void Open() {
		this.open = true;
	}
	
	public void Close() {
		this.open = false;
	}
	
	public Reaction getReaction() {
		return reaction;
	}
	
	@Override
	public String toString() {
		return text;
	}

	@Override
	public int like() {
		this.reaction.Like();
		return this.reaction.getLikes();
	}

	@Override
	public int dislike() {
		this.reaction.Dislike();
		return this.reaction.getDislikes();
	}

	@Override
	public int laugh() {
		this.reaction.Laugh();
		return this.reaction.getLaughs();
	}

	@Override
	public int getLikes() {
		return this.reaction.getLikes();
	}

	@Override
	public int getDislikes() {
		return this.reaction.getDislikes();
	}

	@Override
	public int getLaughs() {
		return this.reaction.getLaughs();
	}
}
