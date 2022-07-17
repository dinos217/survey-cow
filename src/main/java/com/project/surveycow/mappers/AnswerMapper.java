package com.project.surveycow.mappers;

import com.project.surveycow.dtos.AnswerDto;
import com.project.surveycow.entities.Answer;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerMapper {

    AnswerDto answerToAnswerDto(Answer answer);
    Answer answerDtoToAnswer(AnswerDto answerDto);
}
