package nowyouknow.service.controllers.test;

import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.data.Question;
import nowyouknow.service.controllers.QuestionController;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;

public class NykQuestionTester {
  protected static final String QUESTION_RESOURCE = "/question";
  protected static final Question QUESTION_A;
  protected static final Question QUESTION_B;

  static {
    QUESTION_A = new Question("What?");
    QUESTION_A.setId(4L);
    
    QUESTION_B = new Question("Who?");
    QUESTION_B.setId(2L);
  }
  
  @Mock
  protected QuestionDao questionDao;
  
  @InjectMocks
  private QuestionController questionController;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
  }
  
  protected HttpServletResponse getResponse(RequestBuilder request) throws Exception {
    return this.mockMvc.perform(request).andReturn().getResponse();
  }
}
