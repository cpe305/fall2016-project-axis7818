package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Question;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class QuestionControllerTester extends NykQuestionTester {

  @Test
  public void randomQuestionTest() throws Exception {
    List<Question> one = new ArrayList<Question>();
    one.add(QUESTION_A);
    Mockito.when(questionDao.findAll()).thenReturn(one);

    RequestBuilder request = MockMvcRequestBuilders.get(QUESTION_RESOURCE + "/random")
        .contentType(MediaType.APPLICATION_JSON_VALUE);
    HttpServletResponse response = getResponse(request);
    
    Assert.assertEquals(200, response.getStatus());
  }

  @Test
  public void randomQuestionNoQuestionsTest() throws Exception {
    Mockito.when(questionDao.findAll()).thenReturn(new ArrayList<Question>());

    RequestBuilder request = MockMvcRequestBuilders.get(QUESTION_RESOURCE + "/random")
        .contentType(MediaType.APPLICATION_JSON_VALUE);
    HttpServletResponse response = getResponse(request);

    Assert.assertEquals(412, response.getStatus());
  }
}
