package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDto {

    private Long surveyId;
    private Long questionId;
    private String userResponse;
    private Long userId;
    private Boolean isLast;
    private Boolean canceled;
}
