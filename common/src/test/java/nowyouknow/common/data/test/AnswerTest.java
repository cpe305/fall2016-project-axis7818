package nowyouknow.common.data.test;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class AnswerTest {
  public static final String aText = "42";

  @Test
  public void makeAnswerTest() {
    Question question = new Question(QuestionTest.qText);

    Date before = new Date();
    Answer answer = new Answer(question, aText);
    Date after = new Date();

    Assert.assertTrue(before.compareTo(answer.getWhenAnswered()) <= 0);
    Assert.assertTrue(after.compareTo(answer.getWhenAnswered()) >= 0);

    Assert.assertEquals(answer.getQuestion(), question);
    Assert.assertNotNull(answer.getQuestion());

    Assert.assertEquals((int) answer.getReaction().getLikes(), 0);
    Assert.assertEquals((int) answer.getReaction().getDislikes(), 0);
    Assert.assertEquals((int) answer.getReaction().getLaughs(), 0);
  }

  @Test
  public void reactToAnswerTest() {
    Question question = new Question(QuestionTest.qText);
    Answer answer = new Answer(question, aText);

    Assert.assertEquals((int) answer.getReaction().getLikes(), 0);
    Assert.assertEquals((int) answer.getReaction().getDislikes(), 0);
    Assert.assertEquals((int) answer.getReaction().getLaughs(), 0);

    Assert.assertEquals((int) answer.getReaction().like(), 1);
    Assert.assertEquals((int) answer.getReaction().dislike(), 1);
    Assert.assertEquals((int) answer.getReaction().laugh(), 1);

    Assert.assertEquals(answer.getReaction().like(), answer.getReaction().getLikes());
    Assert.assertEquals(answer.getReaction().dislike(), answer.getReaction().getDislikes());
    Assert.assertEquals(answer.getReaction().laugh(), answer.getReaction().getLaughs());
  }
}
