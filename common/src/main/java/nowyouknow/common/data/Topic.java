package nowyouknow.common.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "topic")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String name;

  protected Topic() {}

  public Topic(String name) {
    this.name = name;
  }

  /* GETTERS AND SETTERS */

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /* END GETTERS AND SETTERS */

  @Override
  public String toString() {
    return String.format("<Topic: id=%d>", this.id);
  }
}
