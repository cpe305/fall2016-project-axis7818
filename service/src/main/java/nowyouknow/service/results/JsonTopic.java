package nowyouknow.service.results;

import nowyouknow.common.data.Topic;

public class JsonTopic {
  
  private Long id;
  
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

  private String name;
  
  public JsonTopic() {
    // empty constructor for serialization purposes
  }
  
  public JsonTopic(Topic topic) {
    this.id = topic.getId();
    this.name = topic.getName();
  }
}
