package nowyouknow.service.controllers.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import nowyouknow.common.dao.AnswerDao;
import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.service.controllers.AnswerController;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NykAnswerTester {
  protected static final String ANSWER_RESOURCE = "/answer";
  protected static final ObjectMapper mapper = new ObjectMapper();

  @Mock
  protected AnswerDao answerDao;

  @Mock
  protected ReactionDao reactionDao;

  @Mock
  protected QuestionDao questionDao;

  @InjectMocks
  private AnswerController answerController;

  protected MockMvc mockMvc;

  /**
   * Setup the testing environment.
   */
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(answerController).build();
  }

  protected RequestBuilder postAnswer(String body) {
    return MockMvcRequestBuilders.post(ANSWER_RESOURCE + "/").content(body)
        .contentType(MediaType.APPLICATION_JSON_VALUE);
  }
}
