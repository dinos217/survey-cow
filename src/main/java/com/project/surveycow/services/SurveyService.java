package com.project.surveycow.services;

import com.project.surveycow.dtos.SurveyDto;
import com.project.surveycow.dtos.SurveyRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SurveyService {

    SurveyDto save(SurveyRequestDto surveyRequestDto);

    Page<SurveyDto> findAll(Pageable pageable);

    SurveyDto findById(Long id);

    void delete(Long id);

}
