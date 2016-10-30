package nowyouknow.common.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String text;

  private Boolean open;

  private Date whenAsked;

  @ManyToOne
  private Topic topic;

  @OneToOne
  @NotNull
  private Reaction reaction;

  protected Question() {}

  public Question(String text) {
    this(text, null);
  }

  /**
   * Create a new Question.
   * 
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

  /* GETTERS AND SETTERS */

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Boolean getOpen() {
    return open;
  }

  public void setOpen(Boolean open) {
    this.open = open;
  }

  public Date getWhenAsked() {
    return whenAsked;
  }

  public void setWhenAsked(Date whenAsked) {
    this.whenAsked = whenAsked;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public Reaction getReaction() {
    return reaction;
  }

  public void setReaction(Reaction reaction) {
    this.reaction = reaction;
  }

  /* END GETTERS AND SETTERS */

  public void close() {
    this.open = false;
  }

  @Override
  public String toString() {
    return String.format("<Question: id=%d>", this.id);
  }
}
