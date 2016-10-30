package nowyouknow.service.controllers;

import nowyouknow.common.dao.AnswerDao;
import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Question;

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
  @ResponseBody()
  public String index() {
    return "<p>answer index</p>";
  }

  /**
   * Create a new answer for a specific question.
   * 
   * @param text The text content of the answer.
   * @param questionId the id of the question.
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody()
  public ResponseEntity<Answer> create(String text, Long questionId) {
    Question question = questionDao.findOne(questionId);
    if (question == null) {
      log.error("Could not find question with id {}", questionId);
      return null;
    }

    Answer answer = new Answer(question, text);
    
    log.info("Saving answer {}", answer.getId());
    reactionDao.save(answer.getReaction());
    answerDao.save(answer);

    return ResponseEntity.ok().body(answer);
  }
}
