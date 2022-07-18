package com.project.surveycow.controllers;

import com.project.surveycow.dtos.SurveyDto;
import com.project.surveycow.dtos.SurveyRequestDto;
import com.project.surveycow.services.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/survey")
public class SurveyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SurveyDto> save(@RequestBody SurveyRequestDto surveyRequestDto) {

        logger.info("Started saving survey in database...");

        SurveyDto surveyDto = surveyService.save(surveyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(surveyDto);
    }

}
