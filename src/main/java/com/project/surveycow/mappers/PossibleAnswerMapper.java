package com.project.surveycow.mappers;

import com.project.surveycow.dtos.PossibleAnswerDto;
import com.project.surveycow.entities.PossibleAnswer;
import org.mapstruct.Mapper;

@Mapper
public interface PossibleAnswerMapper {

    PossibleAnswerDto possibleAnswerToPossibleAnswerDto(PossibleAnswer possibleAnswer);
    PossibleAnswer possibleAnswerDtoToPossibleAnswer(PossibleAnswerDto possibleAnswerDto);
}
