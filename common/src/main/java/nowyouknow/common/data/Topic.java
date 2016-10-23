package nowyouknow.common.data;

import java.io.Serializable;

public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	public Long id;
	public String name;
	
	public Topic() { }
	
	@Override
	public String toString() {
		return String.format("<Topic: id=%d>", this.id);
	}
}
