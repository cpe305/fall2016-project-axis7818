package nowyouknow.common.dao;

import nowyouknow.common.data.Answer;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AnswerDao extends CrudRepository<Answer, Long> {

}
