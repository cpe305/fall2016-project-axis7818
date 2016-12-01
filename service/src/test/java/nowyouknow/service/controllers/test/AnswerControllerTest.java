package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;
import nowyouknow.service.results.JsonAnswer;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AnswerControllerTest extends NykAnswerTester {

  @Test
  public void postAnswerTest() throws Exception {
    Question question = new Question("QuestionName");
    question.setId(42L);
    Mockito.when(questionDao.findOne(question.getId())).thenReturn(question);

    Answer answer = new Answer(question, "answer text");
    JsonAnswer jsonAnswer = new JsonAnswer(answer);
    Answer withId = new Answer(question, "answer text");
    withId.setId(76L);
    Mockito.when(answerDao.save(answer)).thenReturn(withId);

    RequestBuilder request = postAnswer(mapper.writeValueAsString(jsonAnswer));
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
        MockMvcResultMatchers.header().string("Location", ANSWER_RESOURCE + "/" + withId.getId()));
  }
  
  @Test
  public void postAnswerNoQuestionTest() throws Exception {
    Long questionId = 43L;
    Mockito.when(questionDao.findOne(questionId)).thenReturn(null);
    
    JsonAnswer jsonAnswer = new JsonAnswer();
    jsonAnswer.setText("ans txt");
    jsonAnswer.setQuestionId(questionId);
    
    RequestBuilder request = postAnswer(mapper.writeValueAsString(jsonAnswer));
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void postAnswerNoTextTest() throws Exception {
    JsonAnswer jsonAnswer = new JsonAnswer();
    
    RequestBuilder request = postAnswer(mapper.writeValueAsString(jsonAnswer));
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
}
