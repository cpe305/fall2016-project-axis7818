package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Topic;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletResponse;

public class TopicControllerTest extends NykTopicTester {

  @Test
  public void createTopicTest() throws Exception {
    Topic noId = new Topic(TOPIC.getName());
    Topic withId = new Topic(TOPIC_NAME);
    withId.setId(TOPIC_ID);
    Mockito.when(topicDao.save(noId)).thenReturn(withId);

    RequestBuilder request = postTopic(TOPIC_JSON);
    HttpServletResponse response = getResponse(request);

    Assert.assertEquals(200, response.getStatus());
    Assert.assertEquals(TOPIC_RESOURCE + "/" + TOPIC_ID, response.getHeader("Location"));
  }

  @Test
  public void createTopicNoNameTest() throws Exception {
    RequestBuilder request = postTopic("{}");
    HttpServletResponse response = getResponse(request);

    Assert.assertEquals(400, response.getStatus());
  }

  @Test
  public void createTopicDuplicateNameTest() throws Exception {
    Mockito.when(topicDao.findByName(TOPIC_NAME)).thenReturn(TOPIC);

    RequestBuilder request = postTopic(TOPIC_JSON);
    HttpServletResponse response = getResponse(request);

    Assert.assertEquals(400, response.getStatus());
  }

  // @Test
  // public void getAllTopicsTest() throws Exception {
  // RequestBuilder request = MockMvcRequestBuilders.get(TOPIC_RESOURCE + "/")
  // .contentType(MediaType.APPLICATION_JSON_VALUE);
  // HttpServletResponse response = getResponse(request);
  //
  // Assert.assertEquals(200, response.getStatus());
  // }
}
