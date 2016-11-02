package nowyouknow.service.results;

import nowyouknow.common.data.Answer;
import nowyouknow.common.data.Reaction;

public class JsonAnswer {
  
  public Long id;
  public String text;
  public Long questionId;
  public Integer likes;
  public Integer dislikes;
  public Integer laughs;

  public JsonAnswer() {}

  /**
   * Uses an Answer object as a template.
   */
  public JsonAnswer(Answer answer) {
    this.id = answer.getId();
    this.text = answer.getText();
    this.questionId = answer.getQuestion().getId();
    
    Reaction reaction = answer.getReaction();
    this.likes = reaction.getLikes();
    this.dislikes = reaction.getDislikes();
    this.laughs = reaction.getLaughs();
  }
}
