package com.project.surveycow.services;

import com.project.surveycow.dtos.SurveyDto;
import com.project.surveycow.dtos.SurveyRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface SurveyService {

    SurveyDto save(SurveyRequestDto surveyRequestDto);

}
