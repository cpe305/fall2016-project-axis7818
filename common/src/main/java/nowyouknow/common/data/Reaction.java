package nowyouknow.common.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reaction")   
public class Reaction {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private Integer likes;
  
  @NotNull
  private Integer dislikes;
  
  @NotNull
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getLikes() {
    return likes;
  }

  public void setLikes(Integer likes) {
    this.likes = likes;
  }

  public Integer getDislikes() {
    return dislikes;
  }

  public void setDislikes(Integer dislikes) {
    this.dislikes = dislikes;
  }

  public Integer getLaughs() {
    return laughs;
  }

  public void setLaughs(Integer laughs) {
    this.laughs = laughs;
  }
  
  /* END GETTERS AND SETTERS */

  public Integer like() {
    return ++this.likes;
  }

  public Integer dislike() {
    return ++this.dislikes;
  }

  public Integer laugh() {
    return ++this.laughs;
  }

  @Override
  public String toString() {
    return String.format("<Reaction: id=%d>", this.id);
  }
}
