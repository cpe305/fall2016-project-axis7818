package nowyouknow.service.results;

import nowyouknow.common.data.Question;
import nowyouknow.common.data.Reaction;

import java.util.Date;

public class JsonQuestion {
  
  public Long id;
  public String text;
  public Boolean open;
  public Date whenAsked;
  public Long topicId;
  public Integer likes;
  public Integer dislikes;
  public Integer laughs;
  
  public JsonQuestion() {}
  
  /**
   * Create a new JsonQuestion based on a Question object.
   */
  public JsonQuestion(Question question) {
    this.id = question.getId();
    this.text = question.getText();
    this.open = question.getOpen();
    this.whenAsked = question.getWhenAsked();
    this.topicId = question.getTopic().getId();

    Reaction reaction = question.getReaction();
    this.likes = reaction.getLikes();
    this.dislikes = reaction.getDislikes();
    this.laughs = reaction.getLaughs();
  }
}
