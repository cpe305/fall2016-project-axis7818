package nowyouknow.service.test.utils;

public class TestUtils {
  /**
   * Create a string of a given length.
   * 
   * @param length the length of the string.
   * @return the resulting string.
   */
  public static String stringOfLength(long length) {
    StringBuilder result = new StringBuilder();
    
    for (int i = 0; i < length; ++i) {
      result.append((char)('a' + (i % 26)));
    }
    
    return result.toString();
  }
}
