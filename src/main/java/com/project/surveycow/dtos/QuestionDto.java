package com.project.surveycow.dtos;

import com.project.surveycow.entities.PossibleAnswer;
import com.project.surveycow.entities.Survey;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto {

    private Long id;
    private String content;
    private Survey survey;
    private List<PossibleAnswerDto> possibleAnswers;

}
