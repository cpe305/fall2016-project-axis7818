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
@Table(name = "answer")
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String text;

  private Date whenAnswered;

  @ManyToOne
  @NotNull
  private Question question;

  @OneToOne
  @NotNull
  private Reaction reaction;

  protected Answer() {}

  /**
   * Create a new Answer.
   * 
   * @param question the question that the answer belongs to.
   * @param text the text content of the Answer
   */
  public Answer(Question question, String text) {
    this.question = question;
    this.text = text;
    this.whenAnswered = new Date();
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

  public Date getWhenAnswered() {
    return whenAnswered;
  }

  public void setWhenAnswered(Date whenAnswered) {
    this.whenAnswered = whenAnswered;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public Reaction getReaction() {
    return reaction;
  }

  public void setReaction(Reaction reaction) {
    this.reaction = reaction;
  }
  
  /* END GETTERS AND SETTERS */

  @Override
  public String toString() {
    return String.format("<Answer id=%d>", this.id);
  }
}
