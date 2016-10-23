package common.data.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;

public class AnswerTest {
	public static final String aText = "42";

	@Test
	public void makeAnswerTest() {
		Question q = new Question(QuestionTest.qText);
		
		Date before = new Date();
		Answer a = new Answer(q, aText);
		Date after = new Date();
				
		Assert.assertTrue(before.compareTo(a.whenAnswered) <= 0);
		Assert.assertTrue(after.compareTo(a.whenAnswered) >= 0);
		
		Assert.assertEquals(a.question, q);
		Assert.assertNotNull(a.question);
		
		Assert.assertEquals((int)a.reaction.likes, 0);
		Assert.assertEquals((int)a.reaction.dislikes, 0);
		Assert.assertEquals((int)a.reaction.laughs, 0);
	}
	
	@Test
	public void reactToAnswerTest() {
		Question q = new Question(QuestionTest.qText);
		Answer a = new Answer(q, aText);
		
		Assert.assertEquals((int)a.reaction.likes, 0);
		Assert.assertEquals((int)a.reaction.dislikes, 0);
		Assert.assertEquals((int)a.reaction.laughs, 0);
		
		Assert.assertEquals((int)a.reaction.like(), 1);
		Assert.assertEquals((int)a.reaction.dislike(), 1);
		Assert.assertEquals((int)a.reaction.laugh(), 1);
		
		Assert.assertEquals(a.reaction.like(), a.reaction.likes);
		Assert.assertEquals(a.reaction.dislike(), a.reaction.dislikes);
		Assert.assertEquals(a.reaction.laugh(), a.reaction.laughs);
	}
}
