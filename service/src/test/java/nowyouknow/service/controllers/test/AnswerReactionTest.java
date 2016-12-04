package nowyouknow.service.controllers.test;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AnswerReactionTest extends NykAnswerTester {
  
  @Test
  public void reactAnswerNotFoundTest() throws Exception {
    Mockito.when(answerDao.findOne(ANSWER.getId())).thenReturn(null);
    
    RequestBuilder request = putAnswer(ANSWER.getId(), "react/dislike", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }
  
  @Test
  public void unknownAnswerReactionTest() throws Exception {
    Mockito.when(answerDao.findOne(ANSWER.getId())).thenReturn(ANSWER);
    Mockito.when(reactionDao.save(ANSWER.getReaction())).thenReturn(ANSWER.getReaction());
    Mockito.when(answerDao.save(ANSWER)).thenReturn(ANSWER);
    
    RequestBuilder request = putAnswer(ANSWER.getId(), "react/raviolli", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void likeAnswerTest() throws Exception {
    Mockito.when(answerDao.findOne(ANSWER.getId())).thenReturn(ANSWER);
    Mockito.when(reactionDao.save(ANSWER.getReaction())).thenReturn(ANSWER.getReaction());
    Mockito.when(answerDao.save(ANSWER)).thenReturn(ANSWER);
    
    RequestBuilder request = putAnswer(ANSWER.getId(), "react/like", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  public void dislikeAnswerTest() throws Exception {
    Mockito.when(answerDao.findOne(ANSWER.getId())).thenReturn(ANSWER);
    Mockito.when(reactionDao.save(ANSWER.getReaction())).thenReturn(ANSWER.getReaction());
    Mockito.when(answerDao.save(ANSWER)).thenReturn(ANSWER);
    
    RequestBuilder request = putAnswer(ANSWER.getId(), "react/dislike", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  public void laughAnswerTest() throws Exception {
    Mockito.when(answerDao.findOne(ANSWER.getId())).thenReturn(ANSWER);
    Mockito.when(reactionDao.save(ANSWER.getReaction())).thenReturn(ANSWER.getReaction());
    Mockito.when(answerDao.save(ANSWER)).thenReturn(ANSWER);
    
    RequestBuilder request = putAnswer(ANSWER.getId(), "react/laugh", "");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }
}
