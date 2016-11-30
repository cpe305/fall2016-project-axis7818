package nowyouknow.service.controllers.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import nowyouknow.common.dao.TopicDao;
import nowyouknow.common.data.Topic;
import nowyouknow.service.controllers.TopicController;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NykTopicTester {
  protected static final String TOPIC_RESOURCE = "/topic";
  protected static final String TOPIC_NAME = "TestTopic";
  protected static final Long TOPIC_ID = 42L;
  protected static final Topic TOPIC;
  protected static final String TOPIC_JSON;
  protected static final ObjectMapper mapper = new ObjectMapper();

  static {
    TOPIC = new Topic(TOPIC_NAME);
    TOPIC.setId(TOPIC_ID);

    TOPIC_JSON = "{\"name\": \"" + TOPIC_NAME + "\", \"id\": \"" + TOPIC_ID + "\"}";
  }

  @Mock
  protected TopicDao topicDao;

  @InjectMocks
  private TopicController topicController;

  protected MockMvc mockMvc;

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
  
  protected RequestBuilder getTopic() {
    return getTopic("");
  }
  
  protected RequestBuilder getTopic(String uri) {
    return MockMvcRequestBuilders.get(TOPIC_RESOURCE + "/" + uri);
  }
}
