package nowyouknow.service.controllers.test;

import nowyouknow.service.controllers.TopicController;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;

public class NykTopicTester {
  private static final String TOPIC_RESOURCE = "/topic";

  @InjectMocks
  private TopicController topicController;

  private MockMvc mockMvc;

  /**
   * Setup the testing environment.
   */
  @Before
  public void setup() {
    // Process mock annotations
    MockitoAnnotations.initMocks(this);

    // Setup Spring test in standalone mode
    this.mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
  }

  protected RequestBuilder postTopic(String body) {
    return MockMvcRequestBuilders.post(TOPIC_RESOURCE + "/").content(body)
        .contentType(MediaType.APPLICATION_JSON_VALUE);
  }

  protected HttpServletResponse getResponse(RequestBuilder request) throws Exception {
    return this.mockMvc.perform(request).andReturn().getResponse();
  }
}
