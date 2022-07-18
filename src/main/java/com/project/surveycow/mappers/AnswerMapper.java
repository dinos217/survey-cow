package com.project.surveycow.mappers;

import com.project.surveycow.dtos.SavedAnswerDto;
import com.project.surveycow.dtos.QuestionResponseDto;
import com.project.surveycow.entities.Answer;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerMapper {

    SavedAnswerDto answerToSavedAnswerDto(Answer answer);
    Answer answerDtoToAnswer(SavedAnswerDto answerDto);
    Answer questionResponseDtoToAnswer(QuestionResponseDto questionResponseDto);
}
