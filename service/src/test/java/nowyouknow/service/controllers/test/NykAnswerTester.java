package nowyouknow.service.controllers.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import nowyouknow.common.dao.AnswerDao;
import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;
import nowyouknow.service.controllers.AnswerController;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NykAnswerTester {
  private static final Logger log = LoggerFactory.getLogger(NykAnswerTester.class);
  protected static final String ANSWER_RESOURCE = "/answer";
  protected static final Question QUESTION;
  protected static final Answer ANSWER;
  protected static final ObjectMapper mapper = new ObjectMapper();

  static {
    QUESTION = new Question("This is a question");
    QUESTION.setId(5736L);
    ANSWER = new Answer(QUESTION, "This is an answer");
    ANSWER.setId(938L);
  }

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

  protected RequestBuilder getAnswer(String uri) {
    return MockMvcRequestBuilders.get(ANSWER_RESOURCE + "/" + uri);
  }

  protected RequestBuilder putAnswer(Long id, String body) {
    return putAnswer(id, "", body);
  }

  protected RequestBuilder putAnswer(Long id, String uri, String body) {
    if (uri != null && !uri.isEmpty()) {
      uri = "/" + uri;
    }
    String request = ANSWER_RESOURCE + "/" + id + uri;
    log.info(request);
    return MockMvcRequestBuilders.put(request).content(body)
        .contentType(MediaType.APPLICATION_JSON_VALUE);
  }
}
