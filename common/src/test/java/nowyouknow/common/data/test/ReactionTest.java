package nowyouknow.common.data.test;

import nowyouknow.common.data.Reaction;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class ReactionTest {

  @Test
  public void gettersAndSettersTest() {
    Reaction reaction = new Reaction();
    Integer num = 4783;
    
    reaction.setLikes(num);
    Assert.assertEquals(reaction.getLikes(), num);
    
    reaction.setDislikes(num);
    Assert.assertEquals(reaction.getDislikes(), num);
    
    reaction.setLaughs(num);
    Assert.assertEquals(reaction.getLaughs(), num);
  }
  
  @Test
  public void methodBehaviorsTest() {
    Long id = 123L;
    Reaction reactionA = new Reaction();
    reactionA.setId(id);
    Reaction reactionB = new Reaction();
    reactionB.setId(id);
    
    Assert.assertTrue(reactionA.equals(reactionB));
    Assert.assertFalse(reactionA.equals(3L));
    
    Assert.assertEquals(reactionA.toString(), "<Reaction: id=123>");
    Assert.assertEquals(reactionA.hashCode(), Objects.hash(123L, 0, 0, 0));
  }
}
