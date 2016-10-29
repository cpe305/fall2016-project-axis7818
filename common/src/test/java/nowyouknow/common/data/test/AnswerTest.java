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

    Assert.assertTrue(before.compareTo(answer.whenAnswered) <= 0);
    Assert.assertTrue(after.compareTo(answer.whenAnswered) >= 0);

    Assert.assertEquals(answer.question, question);
    Assert.assertNotNull(answer.question);

    Assert.assertEquals((int) answer.reaction.likes, 0);
    Assert.assertEquals((int) answer.reaction.dislikes, 0);
    Assert.assertEquals((int) answer.reaction.laughs, 0);
  }

  @Test
  public void reactToAnswerTest() {
    Question question = new Question(QuestionTest.qText);
    Answer answer = new Answer(question, aText);

    Assert.assertEquals((int) answer.reaction.likes, 0);
    Assert.assertEquals((int) answer.reaction.dislikes, 0);
    Assert.assertEquals((int) answer.reaction.laughs, 0);

    Assert.assertEquals((int) answer.reaction.like(), 1);
    Assert.assertEquals((int) answer.reaction.dislike(), 1);
    Assert.assertEquals((int) answer.reaction.laugh(), 1);

    Assert.assertEquals(answer.reaction.like(), answer.reaction.likes);
    Assert.assertEquals(answer.reaction.dislike(), answer.reaction.dislikes);
    Assert.assertEquals(answer.reaction.laugh(), answer.reaction.laughs);
  }
}
