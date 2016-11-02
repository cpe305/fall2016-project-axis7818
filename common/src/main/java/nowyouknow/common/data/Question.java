package nowyouknow.common.data;

import java.util.Date;
import java.util.List;

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

  public Question(String text) {
    this(text, null);
  }

  /**
   * Create a new Question.
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

  public void close() {
    this.open = false;
  }

  @Override
  public String toString() {
    return String.format("<Question: id=%d>", this.id);
  }
}
