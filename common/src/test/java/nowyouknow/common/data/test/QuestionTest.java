package nowyouknow.common.data.test;

import nowyouknow.common.data.Question;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class QuestionTest {
  public static final String qText = "What is the answer to life, the universe, and everything?!";

  @Test
  public void makeQuestionTest() {
    Date before = new Date();
    Question question = new Question(qText);
    Date after = new Date();

    Assert.assertTrue(question.getOpen());

    Assert.assertTrue(before.compareTo(question.getWhenAsked()) <= 0);
    Assert.assertTrue(after.compareTo(question.getWhenAsked()) >= 0);

    Assert.assertNull(question.getTopic());
    Assert.assertNotNull(question.getReaction());

    Assert.assertEquals((int) question.getReaction().getLikes(), 0);
    Assert.assertEquals((int) question.getReaction().getDislikes(), 0);
    Assert.assertEquals((int) question.getReaction().getLaughs(), 0);
  }

  @Test
  public void closeQuestionTest() {
    Question question = new Question(qText);

    Assert.assertTrue(question.getOpen());
    question.close();
    Assert.assertFalse(question.getOpen());
  }

  @Test
  public void reactToQuestionTest() {
    Question question = new Question(qText);

    Assert.assertEquals((int) question.getReaction().getLikes(), 0);
    Assert.assertEquals((int) question.getReaction().getDislikes(), 0);
    Assert.assertEquals((int) question.getReaction().getLaughs(), 0);

    Assert.assertEquals((int) question.getReaction().like(), 1);
    Assert.assertEquals((int) question.getReaction().dislike(), 1);
    Assert.assertEquals((int) question.getReaction().laugh(), 1);

    Assert.assertEquals(question.getReaction().like(), question.getReaction().getLikes());
    Assert.assertEquals(question.getReaction().dislike(), question.getReaction().getDislikes());
    Assert.assertEquals(question.getReaction().laugh(), question.getReaction().getLaughs());
  }
}
