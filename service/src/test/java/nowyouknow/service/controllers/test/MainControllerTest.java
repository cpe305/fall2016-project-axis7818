package nowyouknow.service.controllers.test;

import nowyouknow.common.dao.AnswerDao;
import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.dao.TopicDao;
import nowyouknow.service.controllers.MainController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MainControllerTest {
  
  @Mock
  private TopicDao topicDao;
  
  @Mock
  private QuestionDao questionDao;
  
  @Mock
  private AnswerDao answerDao;
  
  @Mock
  private ReactionDao reactionDao;
  
  @InjectMocks
  private MainController mainController;
  
  private MockMvc mockMvc;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
  }
  
  @Test
  public void nukeTest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.delete("/end/of/the/universe");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
}
