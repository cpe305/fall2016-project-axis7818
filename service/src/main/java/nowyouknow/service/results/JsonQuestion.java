package nowyouknow.service.results;

import nowyouknow.common.data.Question;
import nowyouknow.common.data.Reaction;

import java.util.Date;

public class JsonQuestion {

  private Long id;
  private String text;
  private Boolean open;
  private Date whenAsked;
  private Long topicId;
  private Integer likes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Boolean getOpen() {
    return open;
  }

  public void setOpen(Boolean open) {
    this.open = open;
  }

  public Date getWhenAsked() {
    return whenAsked;
  }

  public void setWhenAsked(Date whenAsked) {
    this.whenAsked = whenAsked;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public Integer getLikes() {
    return likes;
  }

  public void setLikes(Integer likes) {
    this.likes = likes;
  }

  public Integer getDislikes() {
    return dislikes;
  }

  public void setDislikes(Integer dislikes) {
    this.dislikes = dislikes;
  }

  public Integer getLaughs() {
    return laughs;
  }

  public void setLaughs(Integer laughs) {
    this.laughs = laughs;
  }

  private Integer dislikes;
  private Integer laughs;

  public JsonQuestion() {
    // empty constructor for serialization purposes
  }

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
