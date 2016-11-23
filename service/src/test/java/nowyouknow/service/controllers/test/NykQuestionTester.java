package nowyouknow.service.controllers.test;

import nowyouknow.service.controllers.QuestionController;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NykQuestionTester {
  private static final String QUESTION_RESOURCE = "/question";

  @InjectMocks
  private QuestionController questionController;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
  }
}
