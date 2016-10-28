package nowyouknow.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/question")
public class QuestionController {
  @SuppressWarnings("unused")
  private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
}
