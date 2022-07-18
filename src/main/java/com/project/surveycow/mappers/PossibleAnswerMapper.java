package com.project.surveycow.mappers;

import com.project.surveycow.dtos.PossibleAnswerRequestDto;
import com.project.surveycow.entities.PossibleAnswer;
import org.mapstruct.Mapper;

@Mapper
public interface PossibleAnswerMapper {

    PossibleAnswerRequestDto possibleAnswerToPossibleAnswerDto(PossibleAnswer possibleAnswer);
    PossibleAnswer possibleAnswerDtoToPossibleAnswer(PossibleAnswerRequestDto possibleAnswerDto);
}
