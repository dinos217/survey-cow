package com.project.surveycow.mappers;

import com.project.surveycow.dtos.QuestionDto;
import com.project.surveycow.entities.Question;
import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapper {

    QuestionDto questionToQuestionDto(Question question);
    Question questionDtoToQuestion(QuestionDto questionDto);
}
