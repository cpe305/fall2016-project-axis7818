package nowyouknow.service.controllers;

import nowyouknow.common.dao.TopicDao;
import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;
import nowyouknow.service.results.JsonQuestion;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides REST API operations to Topics.
 */
@RestController()
@RequestMapping("/topic")
public class TopicController {
  private static final Logger log = LoggerFactory.getLogger(TopicController.class);

  private static final int MAX_NAME_LENGTH = 256;
  private static final int MAX_DESC_LENGTH = 1024;

  @Autowired
  private TopicDao topicDao;

  /**
   * Create a new topic.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param newTopic the topic object with the new properties.
   * @throws IOException uh oh.
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public void create(HttpServletRequest request, HttpServletResponse response,
      @RequestBody JsonTopic newTopic) throws IOException {
    log.info("POST /topic/create");

    // validate the name
    if (newTopic.getName() == null) {
      log.info("No name provided for topic.");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    if (newTopic.getName().length() > MAX_NAME_LENGTH) {
      log.info("topic name too long...");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    Topic duplicate = topicDao.findByName(newTopic.getName());
    if (duplicate != null) {
      log.info("topic already exists with this name.");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    // validate the description
    if (newTopic.getDescription() != null && newTopic.getDescription().length() > MAX_DESC_LENGTH) {
      log.info("topic description too long...");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    // save the new topic
    log.info("Saving new topic: %s", newTopic.getName());
    Topic topic = topicDao.save(new Topic(newTopic.getName()));

    response.setHeader("Location", "/topic/" + topic.getId());
    return;
  }

  /**
   * Retrieve a list of all Topics.
   * 
   * @param request the request object.
   * @param response the response object.
   * @return a list of all Topic Objects
   */
  @RequestMapping(value = "/", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<JsonTopic> getAll(HttpServletRequest request, HttpServletResponse response) {
    log.info("GET /topic/");
    Iterator<Topic> topics = topicDao.findAll().iterator();
    
    List<JsonTopic> result = new ArrayList<JsonTopic>();
    while (topics.hasNext()) {
      result.add(new JsonTopic(topics.next()));
    }
    return result;
  }

  /**
   * Retrieve a Topic by id or name.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param identifier an identifier, can be id or name.
   * @return a json/http friendly version of the Topic.
   * @throws IOException uh oh.
   */
  @RequestMapping(value = "/{identifier}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public JsonTopic get(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String identifier) throws IOException {
    log.info("GET /topic/" + identifier);
    if (identifier == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return null;
    }
    
    Topic topic = getTopicByIdentifier(identifier);

    if (topic == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    return new JsonTopic(topic);
  }

  /**
   * Get a list of questions for the topic.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param identifier the id or name of the Topic.
   * @return a list of questions that fall under the topic.
   */
  @RequestMapping(value = "/{identifier}/questions", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<JsonQuestion> getQuestions(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String identifier) {
    if (identifier == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return new ArrayList<JsonQuestion>();
    }

    Topic topic = this.getTopicByIdentifier(identifier);
    if (topic == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return new ArrayList<JsonQuestion>();
    }

    List<JsonQuestion> result = new ArrayList<JsonQuestion>();
    for (Question question : topic.getQuestions()) {
      result.add(new JsonQuestion(question));
    }
    return result;
  }

  /**
   * Update a topic.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param identifier an identifier, can be the id or name.
   * @param newTopic the Topic object with the new properties.
   * @return a json/http friendly version of the new Topic.
   * @throws IOException uh oh.
   */
  @RequestMapping(value = "/{identifier}", method = RequestMethod.PUT)
  public JsonTopic update(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String identifier, @RequestBody JsonTopic newTopic) throws IOException {
    if (identifier == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return null;
    }

    // get the current Topic
    Topic topic = this.getTopicByIdentifier(identifier);
    if (topic == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    // name change?
    if (newTopic.getName() != null && !newTopic.getName().isEmpty()
        && !topic.getName().equals(newTopic.getName())) {
      
      // check length
      if (newTopic.getName().length() > MAX_NAME_LENGTH) {
        log.info("Topic name too long...");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
      }
      
      // check for existing topic
      if (topicDao.findByName(newTopic.getName()) != null) {
        log.info("A topic with the name " + newTopic.getName() + " already exists.");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
      }
      
      topic.setName(newTopic.getName());
    }
    
    // description change?
    if (newTopic.getDescription() != null) {
      
      // check length
      if (newTopic.getDescription().length() > MAX_DESC_LENGTH) {
        log.info("Topic description too long...");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
      }
      
      topic.setDescription(newTopic.getDescription());
    }

    topic = topicDao.save(topic);
    return new JsonTopic(topic);
  }

  /**
   * Delete a Topic by id.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param identifier an identifier, can be id or name.
   * @throws IOException uh oh.
   */
  @RequestMapping(value = "/{identifier}", method = RequestMethod.DELETE)
  public void delete(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String identifier) throws IOException {
    if (identifier == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    Topic topic = this.getTopicByIdentifier(identifier);
    if (topic == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    topicDao.delete(topic.getId());
  }
  
  private Topic getTopicByIdentifier(String identifier) {
    Topic topic = null;
    try {
      // first try by id
      topic = topicDao.findOne(Long.parseLong(identifier));
    } catch (NumberFormatException nfe) {
      // otherwise try by name
      topic = topicDao.findByName(identifier);
    }
    return topic;
  }
}
