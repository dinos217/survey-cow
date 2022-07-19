package com.project.surveycow.services;

import com.project.surveycow.dtos.SavedAnswerDto;
import com.project.surveycow.dtos.QuestionResponseDto;
import com.project.surveycow.entities.Answer;
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

@Service
public class AnswerServiceImpl implements AnswerService {

    private static final String TOPIC_NAME = "answer-topic";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);
    private KafkaTemplate<String, Answer> kafkaTemplate;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, KafkaTemplate<String, Answer> kafkaTemplate) {
        this.answerRepository = answerRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    @Override
    public SavedAnswerDto save(QuestionResponseDto questionResponseDto) {

        Answer answer = answerMapper.questionResponseDtoToAnswer(questionResponseDto);
        answer.setCreationTime(LocalDateTime.now());
        Answer savedAnswer = answerRepository.save(answer);
        kafkaTemplate.send(TOPIC_NAME, savedAnswer);

        if (!questionResponseDto.getIsLast()) {

        }

        if (questionResponseDto.getCanceled()) {
            List<Answer> answersToDelete = answerRepository
                    .findAllByUserIdAndSurveyIdAndQuestionId(questionResponseDto.getUserId(), questionResponseDto.getSurveyId(), questionResponseDto.getQuestionId());
            answerRepository.deleteAll(answersToDelete);
            //todo: add kafka msg
        }

        return answerMapper.answerToSavedAnswerDto(savedAnswer);

    }
}
