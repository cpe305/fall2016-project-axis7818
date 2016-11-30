package nowyouknow.service.results;

import nowyouknow.common.data.Topic;

/**
 * a json/http friendly version of a topic object.
 */
public class JsonTopic {

  private Long id;
  private String name;
  private String description;

  public JsonTopic() {
    // empty constructor for serialization purposes
  }

  /**
   * Create a new JsonTopic.
   * @param topic the source Topic object.
   */
  public JsonTopic(Topic topic) {
    this.id = topic.getId();
    this.name = topic.getName();
    this.description = topic.getDescription();
  }

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

  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
}
