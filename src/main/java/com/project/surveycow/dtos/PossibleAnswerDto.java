package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PossibleAnswerDto {

    private Long id;
    private QuestionDto question;
    private String content;
}
