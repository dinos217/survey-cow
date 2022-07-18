package com.project.surveycow.mappers;

import com.project.surveycow.dtos.SurveyDto;
import com.project.surveycow.dtos.SurveyRequestDto;
import com.project.surveycow.entities.Survey;
import org.mapstruct.Mapper;

@Mapper
public interface SurveyMapper {

    SurveyDto surveyToSurveyDto(Survey survey);
    Survey surveyDtoToSurvey(SurveyRequestDto surveyRequestDto);
}
