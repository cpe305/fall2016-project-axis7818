package common.data.test;

import org.junit.Assert;
import org.junit.Test;

import nowyouknow.common.data.Topic;

public class TopicTest {
	public static final String tText = "Hitchhiker's Guide to the Galaxy";
	
	@Test
	public void makeTopicTest() {
		Topic t = new Topic(tText);
		
		Assert.assertEquals(t.getName(), tText);
	}
}
