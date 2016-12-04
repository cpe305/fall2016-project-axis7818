package nowyouknow.service.controllers.test;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class QuestionReactionTest extends NykQuestionTester {

  @Test
  public void reactQuestionNotFoundTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(null);
    
    RequestBuilder request = putQuestion(QUESTION_A.getId(), "react/dislike", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }
  
  @Test
  public void unknownQuestionReactionTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(QUESTION_A);
    Mockito.when(reactionDao.save(QUESTION_A.getReaction())).thenReturn(QUESTION_A.getReaction());
    Mockito.when(questionDao.save(QUESTION_A)).thenReturn(QUESTION_A);
    
    RequestBuilder request = putQuestion(QUESTION_A.getId(), "react/raviolli", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void likeQuestionTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(QUESTION_A);
    Mockito.when(reactionDao.save(QUESTION_A.getReaction())).thenReturn(QUESTION_A.getReaction());
    Mockito.when(questionDao.save(QUESTION_A)).thenReturn(QUESTION_A);
    
    RequestBuilder request = putQuestion(QUESTION_A.getId(), "react/like", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  public void dislikeQuestionTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(QUESTION_A);
    Mockito.when(reactionDao.save(QUESTION_A.getReaction())).thenReturn(QUESTION_A.getReaction());
    Mockito.when(questionDao.save(QUESTION_A)).thenReturn(QUESTION_A);
    
    RequestBuilder request = putQuestion(QUESTION_A.getId(), "react/dislike", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  public void laughQuestionTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(QUESTION_A);
    Mockito.when(reactionDao.save(QUESTION_A.getReaction())).thenReturn(QUESTION_A.getReaction());
    Mockito.when(questionDao.save(QUESTION_A)).thenReturn(QUESTION_A);
    
    RequestBuilder request = putQuestion(QUESTION_A.getId(), "react/laugh", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
}
