package nowyouknow.common.dao;

import nowyouknow.common.data.Question;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Provides basic CRUD operations to Question data.
 */
@Transactional
public interface QuestionDao extends CrudRepository<Question, Long> {
}
