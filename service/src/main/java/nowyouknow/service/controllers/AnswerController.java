package nowyouknow.service.controllers;

import nowyouknow.common.dao.AnswerDao;
import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;

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

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.getWriter().print("<p>answer index</p>");
  }

  /**
   * Create a new answer for a specific question.
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public void create(HttpServletRequest request, HttpServletResponse response,
      @RequestBody Answer answer) throws IOException {
    // validate the answer text
    if (answer.getText() == null || answer.getText().isEmpty()) {
      log.error("No text provided when creating an answer.");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("Please provide some text for the answer.");
      return;
    }

    // retrieve the question
    Question question = questionDao.findOne(answer.getQuestion().getId());
    if (question == null) {
      log.error("Could not find question with id {}", answer.getQuestion().getId());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("This question does not exist.");
      return;
    }

    log.info("Saving answer {}", answer.getText());
    reactionDao.save(answer.getReaction());
    answerDao.save(answer);

    response.setHeader("Location", "/answer/" + answer.getId());
    return;
  }
}
