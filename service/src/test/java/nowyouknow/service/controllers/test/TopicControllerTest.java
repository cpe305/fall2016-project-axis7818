package nowyouknow.service.controllers.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.servlet.http.HttpServletResponse;


public class TopicControllerTest extends NykTopicTester {

  @Test
  public void createTopicNoNameTest() throws Exception {
    RequestBuilder request = postTopic("{}");
    HttpServletResponse response = getResponse(request);

    Assert.assertEquals(400, response.getStatus());
  }

  @Test
  public void createTopicDuplicateNameTest() throws Exception {
    // String body = "{\"name\": \"SAME_NAME\"}";
    // RequestBuilder request = postTopic(body);
    //
    // // make the first topic
    // HttpServletResponse response = getResponse(request);
    // Assert.assertEquals(200, response.getStatus());
    //
    // // make again with same name
    // response = getResponse(request);
    // Assert.assertEquals(400, response.getStatus());
  }
}
