package nowyouknow.service.controllers.test;

import nowyouknow.common.dao.TopicDao;
import nowyouknow.service.controllers.TopicController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;


public class TopicControllerTest {

  @Mock
  private TopicDao topicDao;
  
  @InjectMocks
  private TopicController topicController;
  
  private MockMvc mockMvc;
  
  /**
   * Setup the controller.
   */
  @Before
  public void setup() {
    // Process mock annotations
    MockitoAnnotations.initMocks(this);
    
    // Setup String test in standalone mode
    this.mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
  }
  
  @Test
  public void createTopicTest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get("/");
    HttpServletResponse response = this.mockMvc.perform(request).andReturn().getResponse();
    Assert.assertEquals(404, response.getStatus());
  }
}