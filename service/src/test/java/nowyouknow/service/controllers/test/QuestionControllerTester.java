package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Question;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

public class QuestionControllerTester extends NykQuestionTester {

  @Test
  public void randomQuestionTest() throws Exception {
    List<Question> one = new ArrayList<Question>();
    one.add(QUESTION_A);
    Mockito.when(questionDao.findAll()).thenReturn(one);

    RequestBuilder request = getQuestion("random");
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(QUESTION_A_JSON));
  }

  @Test
  public void randomQuestionNoQuestionsTest() throws Exception {
    Mockito.when(questionDao.findAll()).thenReturn(new ArrayList<Question>());
    
    RequestBuilder request = getQuestion("random");
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(412));
  }
}
