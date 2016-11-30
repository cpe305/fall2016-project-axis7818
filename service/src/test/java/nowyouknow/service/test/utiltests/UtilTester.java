package nowyouknow.service.test.utiltests;

import nowyouknow.service.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class UtilTester {

  @Test
  public void stringOfLengthTest() {
    String str = TestUtils.stringOfLength(5);
    Assert.assertEquals(str, "abcde");
    
    str = TestUtils.stringOfLength(27);
    Assert.assertEquals(str, "abcdefghijklmnopqrstuvwxyza");
  }

}
