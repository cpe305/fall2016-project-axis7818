package nowyouknow.service.results;

import nowyouknow.common.data.Topic;

public class JsonTopic {
  
  public Long id;
  public String name;
  
  public JsonTopic() {}
  
  public JsonTopic(Topic topic) {
    this.id = topic.getId();
    this.name = topic.getName();
  }
}
