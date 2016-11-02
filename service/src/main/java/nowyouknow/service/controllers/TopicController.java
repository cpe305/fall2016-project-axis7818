package nowyouknow.service.controllers;

import nowyouknow.common.dao.TopicDao;
import nowyouknow.common.data.Topic;
import nowyouknow.service.results.JsonTopic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("/topic")
public class TopicController {
  private static final Logger log = LoggerFactory.getLogger(TopicController.class);

  @Autowired
  private TopicDao topicDao;

  /**
   * Create a new topic.
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public void create(HttpServletRequest request, HttpServletResponse response,
      @RequestBody JsonTopic newTopic) throws IOException {
    log.info("POST /topic/create");

    // validate the name
    if (newTopic.name == null) {
      log.info("No name provided for topic.");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("Missing Topic Name");
      return;
    }

    log.info("Saving new topic: %s", newTopic.name);
    Topic topic = topicDao.save(new Topic(newTopic.name));

    response.setHeader("Location", "/topic/" + topic.getId());
    return;
  }

  /**
   * Retrieve a Topic by id.
   */
  @RequestMapping(value = "/{identifier}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public JsonTopic get(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String identifier) throws IOException {
    log.info("GET /topic/" + identifier);
    if (identifier == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("topic identifier required");
      return null;
    }

    Topic topic = null;
    try {
      // first try by id
      topic = topicDao.findOne(Long.parseLong(identifier));
    } catch (NumberFormatException nfe) {
      // otherwise try by name
      topic = topicDao.findByName(identifier);
    }

    if (topic == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("could not find topic identifier: " + identifier);
      return null;
    }

    return new JsonTopic(topic);
  }

  @RequestMapping(value = "/", method = RequestMethod.PUT)
  public void update(HttpServletRequest request, HttpServletResponse response,
      @RequestBody JsonTopic newTopic) {
    // TODO: fill this in
  }

  /**
   * Delete a Topic by id.
   */
  @RequestMapping(value = "/{identifier}", method = RequestMethod.DELETE)
  public void delete(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String identifier) throws IOException {
    if (identifier == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("topic identifier required");
      return;
    }

    try {
      log.info("Trying to delete topic by id...");
      topicDao.delete(Long.parseLong(identifier));
    } catch (NumberFormatException nfe) {
      log.info("Delete by id failed, trying again with name...");
      topicDao.deleteByName(identifier);
    }
  }
}
