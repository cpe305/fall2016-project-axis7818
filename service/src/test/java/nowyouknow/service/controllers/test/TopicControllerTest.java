package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Topic;
import nowyouknow.service.results.JsonTopic;
import nowyouknow.service.test.utils.TestUtils;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

public class TopicControllerTest extends NykTopicTester {

  @Test
  public void getTopicByIdTest() throws Exception {
    Mockito.when(topicDao.findOne(TOPIC_ID)).thenReturn(TOPIC);
    
    String jsonTopic = mapper.writeValueAsString(new JsonTopic(TOPIC));
    
    RequestBuilder request = getTopic(Long.toString(TOPIC_ID));
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().json(jsonTopic));
  }
  
  @Test
  public void getTopicByNameTest() throws Exception {
    Mockito.when(topicDao.findOne(TOPIC_ID)).thenReturn(null);
    Mockito.when(topicDao.findByName(TOPIC_NAME)).thenReturn(TOPIC);
    
    String jsonTopic = mapper.writeValueAsString(new JsonTopic(TOPIC));
    
    RequestBuilder request = getTopic(TOPIC.getName());
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().json(jsonTopic));
  }
  
  @Test
  public void getTopicNotFoundTest() throws Exception {
    Mockito.when(topicDao.findOne(TOPIC_ID)).thenReturn(null);
    Mockito.when(topicDao.findByName(TOPIC_NAME)).thenReturn(null);
    
    RequestBuilder request = getTopic(TOPIC.getName());
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }
  
  @Test
  public void createTopicTest() throws Exception {
    Topic noId = new Topic(TOPIC.getName());
    Topic withId = new Topic(TOPIC_NAME);
    withId.setId(TOPIC_ID);
    Mockito.when(topicDao.save(noId)).thenReturn(withId);

    RequestBuilder request = postTopic(TOPIC_JSON);
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
        MockMvcResultMatchers.header().string("Location", TOPIC_RESOURCE + "/" + TOPIC_ID));
  }

  @Test
  public void createTopicNoNameTest() throws Exception {
    RequestBuilder request = postTopic("{}");

    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  public void createTopicDuplicateNameTest() throws Exception {
    Mockito.when(topicDao.findByName(TOPIC_NAME)).thenReturn(TOPIC);

    RequestBuilder request = postTopic(TOPIC_JSON);

    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void createTopicNameTooLongTest() throws Exception {
    String longName = TestUtils.stringOfLength(300);
    String json = "{\"name\": \"" + longName + "\"}";
    RequestBuilder request = postTopic(json);
    
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void createTopicDescriptionTooLongTest() throws Exception {
    String longDesc = TestUtils.stringOfLength(1500);
    String json = "{\"name\": \"name\", \"description\": \"" + longDesc + "\"}";
    RequestBuilder request = postTopic(json);
    
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  public void getAllTopicsEmptyTest() throws Exception {
    Mockito.when(topicDao.findAll()).thenReturn(new ArrayList<Topic>());

    RequestBuilder request = getTopic();
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getAllTopicsTest() throws Exception {
    List<Topic> topics = new ArrayList<Topic>();
    List<JsonTopic> jsonTopics = new ArrayList<JsonTopic>();
    int count = 14;
    for (int i = 0; i < count; ++i) {
      Topic topic = new Topic("" + i);
      topic.setId((long) i);
      topics.add(topic);
      jsonTopics.add(new JsonTopic(topic));
    }

    Mockito.when(topicDao.findAll()).thenReturn(topics);

    RequestBuilder request = getTopic();
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(jsonTopics)));
  }
}
