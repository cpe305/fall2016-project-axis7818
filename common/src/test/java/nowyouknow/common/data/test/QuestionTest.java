package nowyouknow.common.data.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import nowyouknow.common.data.Question;

public class QuestionTest {
	public static final String qText = "What is the answer to life, the universe, and everything?!";
	
	@Test
	public void makeQuestionTest() {
		Date before = new Date();
		Question q = new Question(qText);
		Date after = new Date();
		
		Assert.assertTrue(q.open);
		
		Assert.assertTrue(before.compareTo(q.whenAsked) <= 0);
		Assert.assertTrue(after.compareTo(q.whenAsked) >= 0);
		
		Assert.assertNull(q.topic);
		Assert.assertNotNull(q.reaction);		
		
		Assert.assertEquals((int)q.reaction.likes, 0);
		Assert.assertEquals((int)q.reaction.dislikes, 0);
		Assert.assertEquals((int)q.reaction.laughs, 0);
	}
	
	@Test
	public void closeQuestionTest() {
		Question q = new Question(qText);
		
		Assert.assertTrue(q.open);
		q.close();
		Assert.assertFalse(q.open);
	}
	
	@Test
	public void reactToQuestionTest() {
		Question q = new Question(qText);
		
		Assert.assertEquals((int)q.reaction.likes, 0);
		Assert.assertEquals((int)q.reaction.dislikes, 0);
		Assert.assertEquals((int)q.reaction.laughs, 0);
		
		Assert.assertEquals((int)q.reaction.like(), 1);
		Assert.assertEquals((int)q.reaction.dislike(), 1);
		Assert.assertEquals((int)q.reaction.laugh(), 1);
		
		Assert.assertEquals(q.reaction.like(), q.reaction.likes);
		Assert.assertEquals(q.reaction.dislike(), q.reaction.dislikes);
		Assert.assertEquals(q.reaction.laugh(), q.reaction.laughs);
	}
}
