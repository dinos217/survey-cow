package com.project.surveycow.services;

import com.project.surveycow.dtos.AnswerDto;
import com.project.surveycow.dtos.QuestionResponseDto;
import com.project.surveycow.entities.Answer;
import com.project.surveycow.mappers.AnswerMapper;
import com.project.surveycow.repositories.AnswerRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerServiceImpl implements AnswerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public AnswerDto save(AnswerDto answerDto) {

        Answer answer = new Answer();
        answer.setCreationTime(LocalDateTime.now());

        for (QuestionResponseDto questionResponseDto : answerDto.getSurveyResponse()) {
            answer.setQuestionId(questionResponseDto.getQuestionId());
            answer.setUserResponse(questionResponseDto.getUserResponse());
            answerRepository.save(answer);
        }

        return answerDto;
    }
}
