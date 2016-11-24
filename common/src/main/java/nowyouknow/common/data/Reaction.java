package nowyouknow.common.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The entity class that represents a Reaction.
 */
@Entity
@Table(name = "reaction")
public class Reaction {

  private Long id;
  private Integer likes;
  private Integer dislikes;
  private Integer laughs;

  /**
   * Create a new reaction.
   */
  public Reaction() {
    this.likes = 0;
    this.dislikes = 0;
    this.laughs = 0;
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
  public Integer getLikes() {
    return likes;
  }

  public void setLikes(Integer likes) {
    this.likes = likes;
  }

  @NotNull
  public Integer getDislikes() {
    return dislikes;
  }

  public void setDislikes(Integer dislikes) {
    this.dislikes = dislikes;
  }

  @NotNull
  public Integer getLaughs() {
    return laughs;
  }

  public void setLaughs(Integer laughs) {
    this.laughs = laughs;
  }

  /* END GETTERS AND SETTERS */

  /**
   * Mark the Reaction with a 'like'.
   * 
   * @return the total number of likes.
   */
  public Integer like() {
    return ++this.likes;
  }

  /**
   * Mark the Reaction with a 'dislike'.
   * 
   * @return the total number of dislikes.
   */
  public Integer dislike() {
    return ++this.dislikes;
  }

  /**
   * Mark the Reaction with a 'laugh'.
   * 
   * @return the total number of laughs.
   */
  public Integer laugh() {
    return ++this.laughs;
  }

  @Override
  public String toString() {
    return String.format("<Reaction: id=%d>", this.id);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Reaction)) {
      return false;
    }

    Reaction otherR = (Reaction) other;
    boolean sameId = id == null ? otherR.getId() == null : id.equals(otherR.getId());
    return sameId && likes.equals(otherR.getLikes()) && dislikes.equals(otherR.getDislikes())
        && laughs.equals(otherR.getLaughs());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, likes, dislikes, laughs);
  }
}
