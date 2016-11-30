package nowyouknow.common.data;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The Entity class that represents a Topic.
 */
@Entity
@Table(name = "topic")
public class Topic {

  private Long id;
  private String name;
  private String description;
  private List<Question> questions;

  protected Topic() {}

  /**
   * Create a new topic with the given name.
   * 
   * @param name the topic's name.
   */
  public Topic(String name) {
    this.name = name;
  }

  /**
   * Create a new topic with the given parameters.
   * 
   * @param name the topic's name.
   * @param description a short description of the topic.
   */
  public Topic(String name, String description) {
    this.name = name;
    this.description = description;
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
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 1024)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic", fetch = FetchType.LAZY)
  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  /* END GETTERS AND SETTERS */

  @Override
  public String toString() {
    return String.format("<Topic: id=%d>", this.id);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Topic)) {
      return false;
    }
    Topic otherTopic = (Topic) other;

    boolean sameId = id == null ? otherTopic.getId() == null : id.equals(otherTopic.getId());
    return sameId && name.equals(otherTopic.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id);
  }
}
