package nowyouknow.common.data.test;

import nowyouknow.common.data.Topic;

import org.junit.Assert;
import org.junit.Test;

public class TopicTest {
  public static final String tText = "Hitchhiker's Guide to the Galaxy";

  @Test
  public void makeTopicTest() {
    Topic topic = new Topic(tText);

    Assert.assertEquals(topic.getName(), tText);
  }
}
