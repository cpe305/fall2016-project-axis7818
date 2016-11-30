package nowyouknow.common.data.test;

import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
  
  @Test
  public void gettersAndSettersTest() {
    Topic topic = new Topic(tText);
    
    Long id = 4L;
    topic.setId(id);
    Assert.assertEquals(topic.getId(), id);
    
    String name = "NEW NAME";
    topic.setName(name);
    Assert.assertEquals(topic.getName(), name);
    
    List<Question> questions = null;
    topic.setQuestions(questions);
    Assert.assertEquals(topic.getQuestions(), questions);
  }
}
