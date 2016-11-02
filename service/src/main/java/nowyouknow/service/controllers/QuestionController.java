package nowyouknow.service.controllers;

import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.dao.TopicDao;
import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;
import nowyouknow.service.results.JsonQuestion;

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
@RequestMapping("/question")
public class QuestionController {
  private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

  @SuppressWarnings("unused")
  @Autowired
  private ReactionDao reactionDao;

  @Autowired
  private QuestionDao questionDao;

  @Autowired
  private TopicDao topicDao;

  /**
   * Create a new Question.
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public void create(HttpServletRequest request, HttpServletResponse response,
      @RequestBody JsonQuestion newQuestion) throws IOException {
    if (newQuestion.text == null || newQuestion.text.isEmpty()) {
      log.error("No text provided when creating a question.");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("Please provide some text for the question.");
      return;
    }

    Topic topic = null;
    if (newQuestion.topicId != null) {
      topic = topicDao.findOne(newQuestion.topicId);
      if (topic == null) {
        log.error("Creating question for topic id {}, but there is no topic with that id!",
            newQuestion.topicId);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().print("This topic does not exist");
        return;
      }
    }

    log.info("Saving new Question: {}", newQuestion.text);
    Question question = new Question(newQuestion.text, topic);
    // reactionDao.save(question.getReaction()); TODO: check if this is still necessary
    questionDao.save(question);

    response.setHeader("Location", "/question/" + question.getId());
  }

  /**
   * Get a question by id.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public JsonQuestion get(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long id) throws IOException {
    if (id == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return null;
    }

    Question question = questionDao.findOne(id);
    if (question == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    return new JsonQuestion(question);
  }

  /**
   * Update a Question.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public JsonQuestion update(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long id, @RequestBody JsonQuestion newQuestion) throws IOException {
    // TODO: check if a PUT to question is necessary.
    response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    return null;
  }

  /**
   * Delete a question.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long id) {
    if (id == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    Question question = questionDao.findOne(id);
    if (question == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    questionDao.delete(question.getId());
  }
}
