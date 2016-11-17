package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;
import nowyouknow.common.data.test.AnswerTest;
import nowyouknow.common.data.test.QuestionTest;
import nowyouknow.common.data.test.TopicTest;
import nowyouknow.service.results.JsonAnswer;
import nowyouknow.service.results.JsonQuestion;
import nowyouknow.service.results.JsonTopic;

import org.junit.Assert;
import org.junit.Test;

public class JsonResponseTest {
  private static final Topic topic = new Topic(TopicTest.tText);
  private static final Question question = new Question(QuestionTest.qText, topic);
  private static final Answer answer = new Answer(question, AnswerTest.aText);
  
  @Test
  public void testJsonTopic() {
    JsonTopic jsonTopic = new JsonTopic(topic);
    
    Assert.assertEquals(jsonTopic.getId(), topic.getId());
    Assert.assertEquals(jsonTopic.getName(), topic.getName());
  }
  
  @Test
  public void testJsonQuestion() {
    JsonQuestion jsonQuestion = new JsonQuestion(question);
    
    Assert.assertEquals(jsonQuestion.getId(), question.getId());
    Assert.assertEquals(jsonQuestion.getText(), question.getText());
    Assert.assertEquals(jsonQuestion.getOpen(), question.getOpen());
    Assert.assertEquals(jsonQuestion.getWhenAsked(), question.getWhenAsked());
    Assert.assertEquals(jsonQuestion.getTopicId(), question.getTopic().getId());
    Assert.assertEquals(jsonQuestion.getLikes(), question.getReaction().getLikes());
    Assert.assertEquals(jsonQuestion.getDislikes(), question.getReaction().getDislikes());
    Assert.assertEquals(jsonQuestion.getLaughs(), question.getReaction().getLaughs());
  }
  
  @Test
  public void testJsonAnswer() {
    JsonAnswer jsonAnswer = new JsonAnswer(answer);
    
    Assert.assertEquals(jsonAnswer.getId(), answer.getId());
    Assert.assertEquals(jsonAnswer.getText(), answer.getText());
    Assert.assertEquals(jsonAnswer.getQuestionId(), answer.getQuestion().getId());
    Assert.assertEquals(jsonAnswer.getLikes(), answer.getReaction().getLikes());
    Assert.assertEquals(jsonAnswer.getDislikes(), answer.getReaction().getDislikes());
    Assert.assertEquals(jsonAnswer.getLaughs(), answer.getReaction().getLaughs());
  }
}
