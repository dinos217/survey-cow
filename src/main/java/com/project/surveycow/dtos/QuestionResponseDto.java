package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDto {

    private Long questionId;
    private String userResponse;
}
