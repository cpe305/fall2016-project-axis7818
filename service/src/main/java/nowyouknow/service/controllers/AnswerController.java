package nowyouknow.service.controllers;

import nowyouknow.common.dao.AnswerDao;
import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;
import nowyouknow.service.results.JsonAnswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides the REST API for Answers.
 */
@RestController()
@RequestMapping("/answer")
public class AnswerController {
  private static final Logger log = LoggerFactory.getLogger(AnswerController.class);

  @Autowired
  private QuestionDao questionDao;

  @Autowired
  private AnswerDao answerDao;

  @Autowired
  private ReactionDao reactionDao;

  /**
   * Create a new Answer.
   * @param request the request object.
   * @param response the response object.
   * @param newAnswer a shallow http friendly version of the answer.
   * @throws IOException uh oh.
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public void create(HttpServletRequest request, HttpServletResponse response,
      @RequestBody JsonAnswer newAnswer) throws IOException {
    // validate the answer text
    if (newAnswer.getText() == null || newAnswer.getText().isEmpty()) {
      log.error("No text provided when creating an answer.");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    // retrieve the question
    Question question = null;
    if (newAnswer.getQuestionId() != null) {
      question = questionDao.findOne(newAnswer.getQuestionId());
    }
    if (question == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    log.info("Saving answer {}", newAnswer.getText());
    Answer answer = new Answer(question, newAnswer.getText());
    reactionDao.save(answer.getReaction());
    answerDao.save(answer);

    response.setHeader("Location", "/answer/" + answer.getId());
    return;
  }
}
