package nowyouknow.service.controllers;

import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.dao.TopicDao;
import nowyouknow.common.data.Question;
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
@RequestMapping("/question")
public class QuestionController {
  private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

  @Autowired
  private ReactionDao reactionDao;

  @Autowired
  private QuestionDao questionDao;
  
  @Autowired
  private TopicDao topicDao;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  public String questionIndex() {
    log.info("question index");
    return "<p>question index</p>";
  }

  /**
   * Create a new Question.
   * 
   * @param text the text for the question
   * @param topicId the id of the topic that the question should fall under
   * @return a String result
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Question> create(String text, Long topicId) {
    Topic topic = topicDao.findOne(topicId);
    if (topic == null) {
      log.error("Could not find topic with id {}", topicId);
      return null;
    }
    
    Question question  = new Question(text, topic);
    log.info("Saving new Question: %s", text);
    reactionDao.save(question.getReaction());
    questionDao.save(question);

    return ResponseEntity.ok().body(question);
  }
}
