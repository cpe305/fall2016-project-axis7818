package common.data.test;

public class QuestionTest {
	public static final String qText = "What is the answer to life, the universe, and everything?!";
	
	/* TEMPORARILY DISABLE TESTING UNTIL THE FUNCTIONALITY IS RE-IMPLEMENTED
	@Test
	public void makeQuestionTest() {
		Date before = new Date();
		Question q = new Question(qText);
		Date after = new Date();
		
		Assert.assertEquals(q.toString(), qText);
		Assert.assertTrue(q.isOpen());
		
		Assert.assertTrue(before.compareTo(q.getWhenAsked()) <= 0);
		Assert.assertTrue(after.compareTo(q.getWhenAsked()) >= 0);
		
		Assert.assertNull(q.getTopic());
		Assert.assertNotNull(q.getReaction());		
		
		Assert.assertEquals(q.getLikes(), 0);
		Assert.assertEquals(q.getDislikes(), 0);
		Assert.assertEquals(q.getLaughs(), 0);
	}
	
	@Test
	public void closeQuestionTest() {
		Question q = new Question(qText);
		
		Assert.assertTrue(q.isOpen());
		q.Close();
		Assert.assertFalse(q.isOpen());
	}
	
	@Test
	public void reactToQuestionTest() {
		Question q = new Question(qText);
		
		Assert.assertEquals(q.getLikes(), 0);
		Assert.assertEquals(q.getDislikes(), 0);
		Assert.assertEquals(q.getLaughs(), 0);
		
		Assert.assertEquals(q.like(), 1);
		Assert.assertEquals(q.dislike(), 1);
		Assert.assertEquals(q.laugh(), 1);
		
		Assert.assertEquals(q.like(), q.getLikes());
		Assert.assertEquals(q.dislike(), q.getDislikes());
		Assert.assertEquals(q.laugh(), q.getLaughs());
	}
	*/
}
