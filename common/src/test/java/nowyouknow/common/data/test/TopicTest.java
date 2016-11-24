package nowyouknow.common.data.test;

import nowyouknow.common.data.Topic;

import org.junit.Assert;
import org.junit.Test;

public class TopicTest {
  public static final String tText = "Hitchhiker's Guide to the Galaxy";

  @Test
  public void makeTopicTest() {
    Topic topic = new Topic(tText);
    Topic otherTopic = new Topic(tText);
    topic.setId(42L);
    otherTopic.setId(42L);

    Assert.assertEquals(topic.getName(), tText);
    Assert.assertTrue(topic.equals(otherTopic));
  }
}
