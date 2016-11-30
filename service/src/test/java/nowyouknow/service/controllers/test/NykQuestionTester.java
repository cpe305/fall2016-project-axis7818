package nowyouknow.service.controllers.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.data.Question;
import nowyouknow.service.controllers.QuestionController;
import nowyouknow.service.results.JsonQuestion;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NykQuestionTester {
  private static final Logger log = LoggerFactory.getLogger(NykQuestionTester.class);

  protected static final String QUESTION_RESOURCE = "/question";
  protected static final Question QUESTION_A;
  protected static String QUESTION_A_JSON;
  protected static final Question QUESTION_B;
  protected static String QUESTION_B_JSON;
  protected static final ObjectMapper mapper = new ObjectMapper();

  static {
    QUESTION_A = new Question("What?");
    QUESTION_A.setId(4L);
    try {
      QUESTION_A_JSON = mapper.writeValueAsString(new JsonQuestion(QUESTION_A));
    } catch (JsonProcessingException ex) {
      log.error("Could not serialize QUESTION_A");
      QUESTION_A_JSON = "";
    }

    QUESTION_B = new Question("Who?");
    QUESTION_B.setId(2L);
    try {
      QUESTION_B_JSON = mapper.writeValueAsString(new JsonQuestion(QUESTION_B));
    } catch (JsonProcessingException ex) {
      log.error("Could not serialize QUETSION_B");
      QUESTION_B_JSON = "";
    }
  }

  @Mock
  protected QuestionDao questionDao;

  @InjectMocks
  private QuestionController questionController;

  protected MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
  }

  protected RequestBuilder getQuestion() {
    return getQuestion("");
  }

  protected RequestBuilder getQuestion(String uri) {
    return MockMvcRequestBuilders.get(QUESTION_RESOURCE + "/" + uri);
  }
}
