package nowyouknow.service.controllers;

import nowyouknow.common.dao.AnswerDao;
import nowyouknow.common.dao.QuestionDao;
import nowyouknow.common.dao.ReactionDao;
import nowyouknow.common.dao.TopicDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
  private static final Logger log = LoggerFactory.getLogger(MainController.class);

  @Autowired
  private TopicDao topicDao;

  @Autowired
  private QuestionDao questionDao;

  @Autowired
  private AnswerDao answerDao;

  @Autowired
  private ReactionDao reactionDao;

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "<h1>Now You Know Index!</h1>";
  }

  /**
   * Would it save you a lot of time if I just gave up and went mad now?.
   */
  @RequestMapping(value = "/end/of/the/universe", method = RequestMethod.DELETE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public void nuke(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("Don't Panic");

    // TODO: check for environment
    topicDao.deleteAll();
    questionDao.deleteAll();
    answerDao.deleteAll();
    reactionDao.deleteAll();

    String message = "So long, and thanks for all the fish!";
    log.info(message);
    response.getWriter().print(message);
  }
}
