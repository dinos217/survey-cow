package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PossibleAnswerRequestDto {

    private Long questionId;
    private String content;
}
