package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SavedAnswerDto {

    private Long id;
    private Long userId;
    private Long surveyId;
    private Long questionId;
    private String userResponse;
    private LocalDateTime creationTime;
}
