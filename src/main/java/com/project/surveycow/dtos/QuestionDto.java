package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto {

    private Long id;
    private String content;
    private Long surveyId;
    private List<PossibleAnswerRequestDto> possibleAnswers;
}
