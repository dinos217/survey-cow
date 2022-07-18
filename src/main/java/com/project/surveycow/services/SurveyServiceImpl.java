package com.project.surveycow.services;

import com.project.surveycow.dtos.PossibleAnswerRequestDto;
import com.project.surveycow.dtos.QuestionDto;
import com.project.surveycow.dtos.SurveyDto;
import com.project.surveycow.dtos.SurveyRequestDto;
import com.project.surveycow.entities.PossibleAnswer;
import com.project.surveycow.entities.Question;
import com.project.surveycow.entities.Survey;
import com.project.surveycow.repositories.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public SurveyDto save(SurveyRequestDto surveyRequestDto) {

        logger.info("Started saving survey");

        Survey survey = new Survey();
        survey.setTitle(surveyRequestDto.getTitle());
        survey.setDescription(surveyRequestDto.getDescription());

        List<Question> questions = new ArrayList<>();
        List<PossibleAnswer> possibleAnswers = new ArrayList<>();

        for (QuestionDto questionDto : surveyRequestDto.getQuestions()) {

            Question question = new Question();
            question.setContent(questionDto.getContent());
            question.setSurvey(survey);

            for (PossibleAnswerRequestDto possibleAnswersRequestDto : questionDto.getPossibleAnswers()) {
                PossibleAnswer possibleAnswer = new PossibleAnswer();
                possibleAnswer.setContent(possibleAnswersRequestDto.getContent());
                possibleAnswer.setQuestion(question);
                possibleAnswers.add(possibleAnswer);
            }

            question.setPossibleAnswers(possibleAnswers);
            questions.add(question);
        }
        survey.setQuestions(questions);

        surveyRepository.save(survey);

        return null;
    }
}
