package nowyouknow.common.data;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {
  private static final long serialVersionUID = 1L;

  public Long id;
  public String text;
  public Boolean open;
  public Date whenAsked;
  public Topic topic;
  public Reaction reaction;

  protected Question() {}

  public Question(String text) {
    this(text, null);
  }

  /**
   * Create a new Question.
   * @param text the question's text.
   * @param topic the topic that this quesiton is part of (null for none)
   */
  public Question(String text, Topic topic) {
    this.text = text;
    this.topic = topic;
    this.open = true;
    this.whenAsked = new Date();
    this.reaction = new Reaction();
  }

  public void close() {
    this.open = false;
  }

  @Override
  public String toString() {
    return String.format("<Question: id=%d>", this.id);
  }
}
