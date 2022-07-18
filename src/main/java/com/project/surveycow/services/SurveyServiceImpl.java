package com.project.surveycow.services;

import com.project.surveycow.dtos.PossibleAnswerRequestDto;
import com.project.surveycow.dtos.QuestionDto;
import com.project.surveycow.dtos.SurveyDto;
import com.project.surveycow.dtos.SurveyRequestDto;
import com.project.surveycow.entities.PossibleAnswer;
import com.project.surveycow.entities.Question;
import com.project.surveycow.entities.Survey;
import com.project.surveycow.mappers.SurveyMapper;
import com.project.surveycow.repositories.SurveyRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SurveyMapper surveyMapper = Mappers.getMapper(SurveyMapper.class);

    private SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Transactional
    @Override
    public SurveyDto save(SurveyRequestDto surveyRequestDto) {

        Survey survey = surveyRepository.save(buildSurvey(surveyRequestDto));

        logger.info("Survey with title: " + survey.getTitle() + " was saved successfully.");

        return buildSurveyDto(survey);
    }

    @Override
    public Page<SurveyDto> findAll(Pageable pageable) {

        Page<Survey> surveysFromDb = surveyRepository.findAll(pageable);
        long total = 0L;

        if (!ObjectUtils.isEmpty(surveysFromDb)) total = surveyRepository.count();

        List<SurveyDto> surveys = surveysFromDb.stream()
                .map(survey -> surveyMapper.surveyToSurveyDto(survey))
                .toList();

        logger.info("Found all surveys successfully.");

        return new PageImpl<>(surveys, pageable, total);
    }

    @Override
    public SurveyDto findById(Long id) {

        Optional<Survey> surveyOptional = surveyRepository.findById(id);

        if (surveyOptional.isEmpty()) {
            return null;
        } else {
            logger.info("Survey with id: " + id + " was found successfully.");
            return buildSurveyDto(surveyOptional.get());
        }
    }

    @Override
    public void delete(Long id) {

        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        surveyOptional.ifPresent(survey -> surveyRepository.delete(survey));
        logger.info("Survey with id: " + id + " was deleted successfully.");
    }

    private Survey buildSurvey(SurveyRequestDto surveyRequestDto) {
        Survey surveyToBeSaved = new Survey();
        surveyToBeSaved.setTitle(surveyRequestDto.getTitle());
        surveyToBeSaved.setDescription(surveyRequestDto.getDescription());

        List<Question> questions = new ArrayList<>();
        List<PossibleAnswer> possibleAnswers = new ArrayList<>();

        for (QuestionDto questionDto : surveyRequestDto.getQuestions()) {

            Question question = new Question();
            question.setContent(questionDto.getContent());
            question.setSurvey(surveyToBeSaved);

            for (PossibleAnswerRequestDto possibleAnswersRequestDto : questionDto.getPossibleAnswers()) {

                PossibleAnswer possibleAnswer = new PossibleAnswer();
                possibleAnswer.setContent(possibleAnswersRequestDto.getContent());
                possibleAnswer.setQuestion(question);
                possibleAnswers.add(possibleAnswer);
            }
            question.setPossibleAnswers(possibleAnswers);
            questions.add(question);
        }
        surveyToBeSaved.setQuestions(questions);
        return surveyToBeSaved;
    }

    private SurveyDto buildSurveyDto(Survey survey) {
        SurveyDto surveyDto = surveyMapper.surveyToSurveyDto(survey);

        surveyDto.getQuestions().forEach(questionDto -> {
            questionDto.setSurveyId(survey.getId());
            questionDto.getPossibleAnswers().forEach(pa -> pa.setQuestionId(questionDto.getId()));
        });
        return surveyDto;
    }
}
