package nowyouknow.common.data;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
  public static final int MAX_TEXT_LENGTH = 512;

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
  @Column(length = MAX_TEXT_LENGTH)
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
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Answer)) {
      return false;
    }
    
    Answer otherA = (Answer) other;
    boolean sameId = id == null ? otherA.getId() == null : id.equals(otherA.getId());
    return sameId && text.equals(otherA.getText());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, text);
  }
}
