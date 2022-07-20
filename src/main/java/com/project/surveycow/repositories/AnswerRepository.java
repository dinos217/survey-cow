package com.project.surveycow.repositories;

import com.project.surveycow.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByUserIdAndSurveyId(Long userId, Long surveyId);

    Optional<Answer> findByUserIdAndSurveyIdAndQuestionId(Long userId, Long surveyId, Long questionId);

    Boolean existsAnswersByUserIdAndSurveyId(Long userId, Long surveyId);

    Boolean existsByUserIdAndSurveyIdAndQuestionId(Long userId, Long surveyId, Long questionId);

}
