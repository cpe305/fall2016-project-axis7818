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

    Assert.assertTrue(question.open);

    Assert.assertTrue(before.compareTo(question.whenAsked) <= 0);
    Assert.assertTrue(after.compareTo(question.whenAsked) >= 0);

    Assert.assertNull(question.topic);
    Assert.assertNotNull(question.reaction);

    Assert.assertEquals((int) question.reaction.likes, 0);
    Assert.assertEquals((int) question.reaction.dislikes, 0);
    Assert.assertEquals((int) question.reaction.laughs, 0);
  }

  @Test
  public void closeQuestionTest() {
    Question question = new Question(qText);

    Assert.assertTrue(question.open);
    question.close();
    Assert.assertFalse(question.open);
  }

  @Test
  public void reactToQuestionTest() {
    Question question = new Question(qText);

    Assert.assertEquals((int) question.reaction.likes, 0);
    Assert.assertEquals((int) question.reaction.dislikes, 0);
    Assert.assertEquals((int) question.reaction.laughs, 0);

    Assert.assertEquals((int) question.reaction.like(), 1);
    Assert.assertEquals((int) question.reaction.dislike(), 1);
    Assert.assertEquals((int) question.reaction.laugh(), 1);

    Assert.assertEquals(question.reaction.like(), question.reaction.likes);
    Assert.assertEquals(question.reaction.dislike(), question.reaction.dislikes);
    Assert.assertEquals(question.reaction.laugh(), question.reaction.laughs);
  }
}
