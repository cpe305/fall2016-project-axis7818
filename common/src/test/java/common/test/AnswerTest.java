package common.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import common.data.Answer;
import common.data.Question;

public class AnswerTest {
	public static final String aText = "42";

	@Test
	public void makeAnswerTest() {
		Question q = new Question(QuestionTest.qText);
		
		Date before = new Date();
		Answer a = new Answer(q, aText);
		Date after = new Date();
		
		Assert.assertEquals(String.format("%s - %s", q.toString(), aText), a.toString());
		
		Assert.assertTrue(before.compareTo(a.getWhenAnswered()) <= 0);
		Assert.assertTrue(after.compareTo(a.getWhenAnswered()) >= 0);
		
		Assert.assertEquals(a.getQuestion(), q);
		Assert.assertNotNull(a.getReaction());
		
		Assert.assertEquals(a.getLikes(), 0);
		Assert.assertEquals(a.getDislikes(), 0);
		Assert.assertEquals(a.getLaughs(), 0);
	}
	
	@Test
	public void reactToAnswerTest() {
		Question q = new Question(QuestionTest.qText);
		Answer a = new Answer(q, aText);
		
		Assert.assertEquals(a.getLikes(), 0);
		Assert.assertEquals(a.getDislikes(), 0);
		Assert.assertEquals(a.getLaughs(), 0);
		
		Assert.assertEquals(a.like(), 1);
		Assert.assertEquals(a.dislike(), 1);
		Assert.assertEquals(a.laugh(), 1);
		
		Assert.assertEquals(a.like(), a.getLikes());
		Assert.assertEquals(a.dislike(), a.getDislikes());
		Assert.assertEquals(a.laugh(), a.getLaughs());
	}
}
