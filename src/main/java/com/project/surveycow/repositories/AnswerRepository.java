package com.project.surveycow.repositories;

import com.project.surveycow.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByUserIdAndSurveyId(Long userId, Long surveyId);

    Answer findByUserIdAndSurveyIdAndQuestionId(Long userId, Long surveyId, Long questionId);

    Boolean existsAnswersByUserIdAndSurveyId(Long userId, Long surveyId);

}
