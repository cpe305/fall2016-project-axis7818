package nowyouknow.service.results;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Reaction;

public class JsonAnswer {
  
  private Long id;
  private String text;
  private Long questionId;
  private Integer likes;
  private Integer dislikes;
  private Integer laughs;

  public JsonAnswer() {}

  /**
   * Uses an Answer object as a template.
   */
  public JsonAnswer(Answer answer) {
    this.setId(answer.getId());
    this.setText(answer.getText());
    this.setQuestionId(answer.getQuestion().getId());
    
    Reaction reaction = answer.getReaction();
    this.setLikes(reaction.getLikes());
    this.setDislikes(reaction.getDislikes());
    this.setLaughs(reaction.getLaughs());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getLikes() {
    return likes;
  }

  public void setLikes(Integer likes) {
    this.likes = likes;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public Integer getLaughs() {
    return laughs;
  }

  public void setLaughs(Integer laughs) {
    this.laughs = laughs;
  }

  public Integer getDislikes() {
    return dislikes;
  }

  public void setDislikes(Integer dislikes) {
    this.dislikes = dislikes;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
