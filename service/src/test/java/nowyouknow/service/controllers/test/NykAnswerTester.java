package nowyouknow.service.controllers.test;

import nowyouknow.service.controllers.AnswerController;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NykAnswerTester {
  private static final String ANSWER_RESOURCE = "/answer";

  @InjectMocks
  private AnswerController answerController;

  private MockMvc mockMvc;

  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(answerController).build();
  }
}
