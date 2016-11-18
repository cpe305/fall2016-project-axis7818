package nowyouknow.service.website;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
  @SuppressWarnings("unused")
  private static final Logger log = LoggerFactory.getLogger(HomeController.class);

  /**
   * Index page.
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    // log.info("GET /");
    // model.addAttribute("name", "Cameron");
    // return "public/templates/index.html";
    return "index";
  }

}
