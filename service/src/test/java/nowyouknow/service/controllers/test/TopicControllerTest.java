package nowyouknow.service.controllers.test;

import nowyouknow.common.dao.TopicDao;
import nowyouknow.service.controllers.TopicController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class TopicControllerTest {
  // private static final String TOPIC_RESOURCE = "/topic";

  @Mock
  private TopicDao topicDao;

  @InjectMocks
  private TopicController topicController;

  // private MockMvc mockMvc;

  /**
   * Setup the controller.
   */
  @Before
  public void setup() {
    // Process mock annotations
    MockitoAnnotations.initMocks(this);

    // Setup String test in standalone mode
    // this.mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
  }

  @Test
  public void createTopicTest() throws Exception {
    // RequestBuilder request = MockMvcRequestBuilders.post(TOPIC_RESOURCE + "/")
    // .content("{\"name\": \"HHGTTG\"}").contentType(MediaType.APPLICATION_JSON_VALUE);
    // HttpServletResponse response = this.mockMvc.perform(request).andReturn().getResponse();
    // Assert.assertEquals(200, response.getStatus());
  }
}
