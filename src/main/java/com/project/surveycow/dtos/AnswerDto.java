package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class  AnswerDto {

    private Long id;
    private Long surveyId;
    private Long userId;
    private List<QuestionResponseDto> surveyResponse;
}
