package nowyouknow.service.controllers;

import nowyouknow.common.dao.TopicDao;
import nowyouknow.common.data.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping("/topic")
public class TopicController {
  private static final Logger log = LoggerFactory.getLogger(TopicController.class);

  @Autowired
  private TopicDao topicDao;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  public String topicIndex() {
    log.info("topic index");
    return "<p>topic index</p>";
  }

  /**
   * Create a new Topic.
   * 
   * @param name the name of the topic
   * @return A String result
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Topic> create(String name) {
    Topic topic = new Topic(name);
    
    log.info("Saving new topic: %s", name);
    topicDao.save(topic);

    return ResponseEntity.ok().body(topic);
  }
}
