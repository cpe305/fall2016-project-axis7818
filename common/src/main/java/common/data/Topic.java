package common.data;

public class Topic {
	private long id;
	private String name;
	
	public Topic() { }
	
	public Topic(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
}
