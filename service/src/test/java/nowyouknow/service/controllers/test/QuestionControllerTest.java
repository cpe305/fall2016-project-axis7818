package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;
import nowyouknow.service.results.JsonAnswer;
import nowyouknow.service.results.JsonQuestion;
import nowyouknow.service.test.utils.TestUtils;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class QuestionControllerTest extends NykQuestionTester {

  @Test
  public void getAllQuestionsTest() throws Exception {
    List<Question> questions = new ArrayList<Question>();
    List<JsonQuestion> jsonQuestions = new ArrayList<JsonQuestion>();

    int count = 12;
    for (int i = 0; i < count; ++i) {
      Question question = new Question("" + i);
      question.setId((long) i);
      questions.add(question);
      jsonQuestions.add(new JsonQuestion(question));
    }

    Mockito.when(questionDao.findAll()).thenReturn(questions);

    String body = mapper.writeValueAsString(jsonQuestions);
    RequestBuilder request = getQuestion();
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(body));
  }

  @Test
  public void deleteQuestionTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(QUESTION_A);

    RequestBuilder request = deleteQuestion(QUESTION_A.getId());
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  public void deleteQuestionNotFoundTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(null);

    RequestBuilder request = deleteQuestion(QUESTION_A.getId());
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }

  @Test
  public void getQuestionAnswersTest() throws Exception {
    Question question = new Question("???");
    int count = 12;
    question.setId((long) count);

    List<Answer> answers = new ArrayList<Answer>();
    List<JsonAnswer> jsonAnswers = new ArrayList<JsonAnswer>();
    for (int i = 0; i < count; ++i) {
      Answer answer = new Answer(question, TestUtils.stringOfLength(i));
      answers.add(answer);
      jsonAnswers.add(new JsonAnswer(answer));
    }
    question.setAnswers(answers);

    Mockito.when(questionDao.findOne(question.getId())).thenReturn(question);
    String body = mapper.writeValueAsString(jsonAnswers);

    RequestBuilder request = getQuestion(Long.toString(question.getId()) + "/answers");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(body));
  }

  @Test
  public void getQuestionAnswersNotFoundTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(null);

    RequestBuilder request = getQuestion(Long.toString(QUESTION_A.getId()) + "/answers");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }

  @Test
  public void getQuestionTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(QUESTION_A);

    String body = mapper.writeValueAsString(new JsonQuestion(QUESTION_A));

    RequestBuilder request = getQuestion(Long.toString(QUESTION_A.getId()));
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(body));
  }

  @Test
  public void getQuestionNotFoundTest() throws Exception {
    Mockito.when(questionDao.findOne(QUESTION_A.getId())).thenReturn(null);

    RequestBuilder request = getQuestion(Long.toString(QUESTION_A.getId()));
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }

  @Test
  public void postQuestionTest() throws Exception {
    Topic topic = new Topic("topic name");
    topic.setId(1L);

    Question question = new Question("q text", topic);
    Question withId = new Question(question.getText(), topic);
    withId.setId(4L);

    Mockito.when(topicDao.findOne(1L)).thenReturn(topic);
    Mockito.when(questionDao.save(question)).thenReturn(withId);

    String body = mapper.writeValueAsString(new JsonQuestion(question));
    RequestBuilder request = postQuestion(body);
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.header().string("Location", QUESTION_RESOURCE + "/" + 4));
  }

  @Test
  public void postQuestionNoTextTest() throws Exception {
    Question question = new Question("");
    String jsonQuestion = mapper.writeValueAsString(new JsonQuestion(question));

    RequestBuilder request = postQuestion(jsonQuestion);
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  public void postQuestionUnknownTopicTest() throws Exception {
    Long id = 123L;
    JsonQuestion jsonQuestion = new JsonQuestion(QUESTION_A);
    jsonQuestion.setTopicId(id);
    String body = mapper.writeValueAsString(jsonQuestion);

    Mockito.when(topicDao.findOne(id)).thenReturn(null);

    RequestBuilder request = postQuestion(body);
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  public void randomQuestionTest() throws Exception {
    List<Question> one = new ArrayList<Question>();
    one.add(QUESTION_A);
    Mockito.when(questionDao.findAll()).thenReturn(one);

    String body = mapper.writeValueAsString(new JsonQuestion(QUESTION_A));
    RequestBuilder request = getQuestion("random");
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(body));
  }

  @Test
  public void randomQuestionNoQuestionsTest() throws Exception {
    Mockito.when(questionDao.findAll()).thenReturn(new ArrayList<Question>());

    RequestBuilder request = getQuestion("random");
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(412));
  }

  @Test
  public void randomQuestionExcludingIdTest() throws Exception {
    List<Question> questions = new ArrayList<Question>();
    questions.add(QUESTION_A);
    questions.add(QUESTION_B);
    Mockito.when(questionDao.findAll()).thenReturn(questions);

    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("excludeId", Long.toString(QUESTION_A.getId()));

    RequestBuilder request = getQuestion("random", params);
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(QUESTION_B_JSON));
  }
}
