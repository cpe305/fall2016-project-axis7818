package nowyouknow.common.dao;

import nowyouknow.common.data.Question;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface QuestionDao extends CrudRepository<Question, Long> {
}
