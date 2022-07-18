package com.project.surveycow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyDto {

    private Long id;
    private String title;
    private String description;
    private List<QuestionDto> questions;
}
