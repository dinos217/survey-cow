package com.project.surveycow.services;

import com.project.surveycow.dtos.QuestionResponseDto;
import com.project.surveycow.dtos.SavedAnswerDto;
import com.project.surveycow.entities.Answer;
import com.project.surveycow.exceptions.EntityNotFoundException;
import com.project.surveycow.exceptions.SurveyAlreadyTakenException;
import com.project.surveycow.mappers.AnswerMapper;
import com.project.surveycow.repositories.AnswerRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private static final String TOPIC_NAME = "answer-topic";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.answerRepository = answerRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    @Override
    public SavedAnswerDto save(QuestionResponseDto questionResponseDto) {

        if (answerRepository.existsByUserIdAndSurveyIdAndQuestionId(questionResponseDto.getUserId(),
                questionResponseDto.getSurveyId(), questionResponseDto.getQuestionId())) {
            logger.info("User with id: " + questionResponseDto.getUserId() +
                    " has already taken the Survey with id: " + questionResponseDto.getSurveyId());
            throw new SurveyAlreadyTakenException("User with id: " + questionResponseDto.getUserId() +
                    " has already taken the Survey with id: " + questionResponseDto.getSurveyId());
        }

        Answer answer = answerMapper.questionResponseDtoToAnswer(questionResponseDto);
        answer.setCreationTime(LocalDateTime.now());
        Answer savedAnswer = answerRepository.save(answer);

        logger.info("New answer saved with id: " + savedAnswer.getId());
//        kafkaTemplate.send(TOPIC_NAME, savedAnswer);

        if (!questionResponseDto.getIsLast()) {
            //todo: add kafka msg
        }

        return answerMapper.answerToSavedAnswerDto(savedAnswer);
    }

    @Transactional
    @Override
    public SavedAnswerDto update(QuestionResponseDto questionResponseDto) {

        Optional<Answer> answerOptional = answerRepository.findByUserIdAndSurveyIdAndQuestionId(questionResponseDto.getUserId(),
                questionResponseDto.getSurveyId(), questionResponseDto.getQuestionId());

        if (answerOptional.isEmpty()) throw new EntityNotFoundException("Survey Id: " + questionResponseDto.getSurveyId() +
                ", Question Id: " + questionResponseDto.getQuestionId() + ": Answer was not found.");

        Answer answerToUpdate = answerOptional.get();
        answerToUpdate.setUserResponse(questionResponseDto.getUserResponse());
        answerToUpdate.setUpdateTime(LocalDateTime.now());

        logger.info("Updating user response to question with question id: " + questionResponseDto.getQuestionId());

        return answerMapper.answerToSavedAnswerDto(answerRepository.save(answerToUpdate));
    }

    @Override
    public void deletePreviousAnswers(QuestionResponseDto questionResponseDto) {

        List<Answer> answersToDelete = answerRepository.findAllByUserIdAndSurveyId(questionResponseDto.getUserId(),
                questionResponseDto.getSurveyId());

        answerRepository.deleteAll(answersToDelete);

        logger.info("Deleting all responses of survey: " + questionResponseDto.getSurveyId() +
                " because user with id: " + questionResponseDto.getUserId() + " canceled the process.");
    }
}
