package nowyouknow.common.data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The Entity class that represents a Question.
 */
@Entity
@Table(name = "question")
public class Question {

  private Long id;
  private String text;
  private Boolean open;
  private Date whenAsked;
  private Topic topic;
  private Reaction reaction;
  private List<Answer> answers;

  protected Question() {}

  /**
   * Create a new Question.
   * 
   * @param text the text for the question.
   */
  public Question(String text) {
    this(text, null);
  }

  /**
   * Create a new Question.
   * 
   * @param text the text for the question.
   * @param topic the topic that the question belongs to.
   */
  public Question(String text, Topic topic) {
    this.text = text;
    this.topic = topic;
    this.open = true;
    this.whenAsked = new Date();
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

  @ManyToOne(cascade = CascadeType.ALL)
  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  @OneToOne
  @NotNull
  public Reaction getReaction() {
    return reaction;
  }

  public void setReaction(Reaction reaction) {
    this.reaction = reaction;
  }

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.LAZY)
  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  /* END GETTERS AND SETTERS */

  /**
   * Close the question to answers.
   */
  public void close() {
    this.open = false;
  }

  @Override
  public String toString() {
    return String.format("<Question: id=%d>", this.id);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Question)) {
      return false;
    }

    Question otherQ = (Question) other;
    boolean sameId = id == null ? otherQ.getId() == null : id.equals(otherQ.getId());
    return sameId && text.equals(otherQ.getText());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, text);
  }
}
