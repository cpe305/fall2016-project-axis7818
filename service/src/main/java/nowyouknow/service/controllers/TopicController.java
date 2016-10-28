package nowyouknow.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nowyouknow.common.data.Topic;

@Controller()
@RequestMapping("/topic")
public class TopicController {
	private static final Logger log = LoggerFactory.getLogger(TopicController.class);
	
//	@Autowired
//	private TopicDAO topicDao;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public String create(String name) {		
		Topic topic = null;
		
		try {
			topic = new Topic(name);
			log.info("Saving new topic: %s", name);
//			topicDao.save(topic);
		}
		catch (Exception e) {
			return String.format("Error creating Topic (%s): %s", name, e.getMessage());
		}
		
		return String.format("Topic (%s) created successfully!", topic.getName());
	}
}
