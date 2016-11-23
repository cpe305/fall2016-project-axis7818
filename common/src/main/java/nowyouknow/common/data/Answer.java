package nowyouknow.common.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The entity object that represents and Answer.
 */
@Entity
@Table(name = "answer")
public class Answer {

  private Long id;
  private String text;
  private Date whenAnswered;
  private Question question;
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
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @NotNull
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
  
  @ManyToOne(cascade = CascadeType.ALL)
  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  @OneToOne
  @NotNull
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
