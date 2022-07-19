package com.project.surveycow.services;

import com.project.surveycow.dtos.SavedAnswerDto;
import com.project.surveycow.dtos.QuestionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {

    SavedAnswerDto save(QuestionResponseDto questionResponseDto);

    SavedAnswerDto update(QuestionResponseDto questionResponseDto);

    void deletePreviousAnswers(QuestionResponseDto questionResponseDto);
}
