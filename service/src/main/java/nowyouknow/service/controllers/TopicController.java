package nowyouknow.service.controllers;

import nowyouknow.common.data.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping("/topic")
public class TopicController {
  private static final Logger log = LoggerFactory.getLogger(TopicController.class);

  // @Autowired
  // private TopicDAO topicDao;

  /**
   * Create a new Topic.
   * 
   * @param name the name of the topic
   * @return A String result
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public String create(String name) {
    Topic topic = null;

    try {
      topic = new Topic(name);
      log.info("Saving new topic: %s", name);
      // topicDao.save(topic);
    } catch (Exception ex) {
      return String.format("Error creating Topic (%s): %s", name, ex.getMessage());
    }

    return String.format("Topic (%s) created successfully!", topic.getName());
  }
}
