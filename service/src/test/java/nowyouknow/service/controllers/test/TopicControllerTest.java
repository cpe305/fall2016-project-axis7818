package nowyouknow.service.controllers.test;

import nowyouknow.common.data.Question;
import nowyouknow.common.data.Topic;
import nowyouknow.service.results.JsonQuestion;
import nowyouknow.service.results.JsonTopic;
import nowyouknow.service.test.utils.TestUtils;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

public class TopicControllerTest extends NykTopicTester {

  @Test
  public void updateTopicTest() throws Exception {
    JsonTopic jsonTopic = new JsonTopic();
    jsonTopic.setName("new Name");
    jsonTopic.setDescription("new description");
    jsonTopic.setId(TOPIC.getId());
    
    Topic newTopic = new Topic(jsonTopic.getName());
    newTopic.setId(TOPIC.getId());
    JsonTopic resultTopic = new JsonTopic(newTopic);
    
    Mockito.when(topicDao.findOne(TOPIC.getId())).thenReturn(TOPIC);
    Mockito.when(topicDao.save(newTopic)).thenReturn(newTopic);
    
    String body = mapper.writeValueAsString(jsonTopic);
    String resultBody = mapper.writeValueAsString(resultTopic);
    RequestBuilder request = putTopic(jsonTopic.getId(), body);
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().json(resultBody));
  }
  
  @Test
  public void updateTopicDescriptionTooLongTest() throws Exception {
    JsonTopic jsonTopic = new JsonTopic();
    jsonTopic.setName(TOPIC.getName());
    jsonTopic.setDescription(TestUtils.stringOfLength(1234));
    String body = mapper.writeValueAsString(jsonTopic);
    
    Mockito.when(topicDao.findOne(TOPIC.getId())).thenReturn(TOPIC);
    
    RequestBuilder request = putTopic(TOPIC.getId(), body);
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void updateTopicDuplicateNameTest() throws Exception {
    Topic sameName = new Topic(TOPIC.getName());
    Topic original = new Topic("ajfdklashfjkdsla");
    original.setId(7L);
    Mockito.when(topicDao.findOne(original.getId())).thenReturn(original);
    Mockito.when(topicDao.findByName(sameName.getName())).thenReturn(sameName);
    
    JsonTopic jsonTopic = new JsonTopic();
    jsonTopic.setName(sameName.getName());
    
    RequestBuilder request = putTopic(original.getId(), mapper.writeValueAsString(jsonTopic));
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void updateTopicNameTooLongTest() throws Exception {
    Mockito.when(topicDao.findOne(TOPIC.getId())).thenReturn(TOPIC);
    
    JsonTopic jsonTopic = new JsonTopic();
    jsonTopic.setName(TestUtils.stringOfLength(300));
    
    RequestBuilder request = putTopic(TOPIC.getId(), mapper.writeValueAsString(jsonTopic));
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(400));
  }
  
  @Test
  public void updateTopicNotFoundTest() throws Exception {
    Mockito.when(topicDao.findOne(TOPIC.getId())).thenReturn(null);
    
    RequestBuilder request = putTopic(TOPIC.getId(), TOPIC_JSON);
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }
  
  @Test
  public void deleteTopicNotFoundTest() throws Exception {
    Mockito.when(topicDao.findOne(TOPIC_ID)).thenReturn(null);

    mockMvc.perform(MockMvcRequestBuilders.delete(TOPIC_RESOURCE + "/" + TOPIC_ID))
        .andExpect(MockMvcResultMatchers.status().is(404));
  }
  
  @Test
  public void deleteTopicTest() throws Exception {
    Mockito.when(topicDao.findOne(TOPIC_ID)).thenReturn(TOPIC);

    mockMvc.perform(MockMvcRequestBuilders.delete(TOPIC_RESOURCE + "/" + TOPIC_ID))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  public void getTopicQuestionsUnknownTopicTest() throws Exception {
    Mockito.when(topicDao.findByName(TOPIC_NAME)).thenReturn(null);

    RequestBuilder request = getTopic(TOPIC_NAME + "/questions");
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(404));
  }

  @Test
  public void getTopicQuestionsTest() throws Exception {
    Topic topic = new Topic("Topic Name");
    topic.setId(TOPIC_ID);
    List<Question> questions = new ArrayList<Question>();
    int count = 10;
    for (int i = 0; i < count; ++i) {
      questions.add(new Question(Integer.toString(i), topic));
    }
    topic.setQuestions(questions);

    Mockito.when(topicDao.findOne(topic.getId())).thenReturn(topic);

    List<JsonQuestion> jsonQuesions = new ArrayList<JsonQuestion>();
    for (Question question : questions) {
      jsonQuesions.add(new JsonQuestion(question));
    }
    String jsonQuestionsString = mapper.writeValueAsString(jsonQuesions);

    RequestBuilder request = getTopic(topic.getId() + "/questions");
    this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonQuestionsString));
  }

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
