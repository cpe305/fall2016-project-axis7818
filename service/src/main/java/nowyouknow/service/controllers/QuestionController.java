package nowyouknow.service.controllers;

import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.dao.TopicDao;
import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;
import nowyouknow.service.results.JsonAnswer;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides REST API operations for Questions.
 */
@RestController()
@RequestMapping("/question")
public class QuestionController {
  private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
  private static final Random random = new Random(System.currentTimeMillis());

  @Autowired
  private ReactionDao reactionDao;

  @Autowired
  private QuestionDao questionDao;

  @Autowired
  private TopicDao topicDao;

  /**
   * Create a new Question.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param newQuestion a shallow http friendly version of the new Question.
   * @throws IOException uh oh.
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public void create(HttpServletRequest request, HttpServletResponse response,
      @RequestBody JsonQuestion newQuestion) throws IOException {
    if (newQuestion.getText() == null || newQuestion.getText().isEmpty()) {
      log.error("No text provided when creating a question.");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("Please provide some text for the question.");
      return;
    }

    Topic topic = null;
    if (newQuestion.getTopicId() != null) {
      topic = topicDao.findOne(newQuestion.getTopicId());
      if (topic == null) {
        log.error("Creating question for topic id {}, but there is no topic with that id!",
            newQuestion.getTopicId());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().print("This topic does not exist");
        return;
      }
    }

    log.info("Saving new Question: {}", newQuestion.getText());
    Question question = new Question(newQuestion.getText(), topic);
    reactionDao.save(question.getReaction());
    question = questionDao.save(question);

    response.setHeader("Location", "/question/" + question.getId());
  }

  /**
   * Get a question by id.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param id the id of the Question.
   * @return the json/http friendly version of the Question.
   * @throws IOException uh oh.
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
   * Get a random question in the database.
   * 
   * @param request the request object.
   * @param response the response object.
   * @return a json/http friendly version of the question.
   */
  @RequestMapping(value = "/random", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public JsonQuestion getRandom(HttpServletRequest request, HttpServletResponse response) {
    Long excludeId = null;
    try {
      excludeId = Long.parseLong(request.getParameter("excludeId"));
    } catch (NumberFormatException nfe) {
      log.info("Could not determine excludeId.");
    }

    Iterator<Question> questionIter = questionDao.findAll().iterator();

    List<Question> questions = new ArrayList<Question>();
    while (questionIter.hasNext()) {
      Question nextQ = questionIter.next();
      if (!nextQ.getId().equals(excludeId)) {
        questions.add(nextQ);
      }
    }

    if (questions.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
      return null;
    }

    int index = random.nextInt(questions.size());
    return new JsonQuestion(questions.get(index));
  }

  /**
   * Get all answers for a question.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param id the id of the Question.
   * @return a List of json/http friendly Answers for the question.
   */
  @RequestMapping(value = "/{id}/answers", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<JsonAnswer> getAnswers(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long id) {
    if (id == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return new ArrayList<JsonAnswer>();
    }

    Question question = questionDao.findOne(id);
    if (question == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return new ArrayList<JsonAnswer>();
    }

    List<Answer> answers = question.getAnswers();
    List<JsonAnswer> result = new ArrayList<JsonAnswer>(answers.size());
    for (Answer ans : answers) {
      result.add(new JsonAnswer(ans));
    }
    return result;
  }

  /**
   * Update a Question.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param id the id of the question to change.
   * @param newQuestion the question object with the new properties.
   * @return the new version of the question.
   * @throws IOException uh oh.
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
   * React to a question.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param id the id of the question.
   * @param reactionType "like", "dislike", or "laugh"
   * @throws IOException uhoh.
   */
  @RequestMapping(value = "/{id}/react/{reactionType}", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void react(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String reactionType, @PathVariable Long id) throws IOException {
    // get the question
    Question question = questionDao.findOne(id);
    if (question == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    
    // react
    if ("like".equals(reactionType)) {
      question.getReaction().like();
    } else if ("dislike".equals(reactionType)) {
      question.getReaction().dislike();
    } else if ("laugh".equals(reactionType)) {
      question.getReaction().laugh();
    } else {
      log.info("Unknown reactionType: " + reactionType);
      // invalid reaction type
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    
    // save and return
    reactionDao.save(question.getReaction());
  }

  /**
   * Delete a question.
   * 
   * @param request the request object.
   * @param response the response object.
   * @param id the id of the question to delete.
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
