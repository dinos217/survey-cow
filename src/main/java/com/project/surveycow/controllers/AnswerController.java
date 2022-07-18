package com.project.surveycow.controllers;

import com.project.surveycow.dtos.QuestionResponseDto;
import com.project.surveycow.dtos.SavedAnswerDto;
import com.project.surveycow.services.AnswerService;
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
@RequestMapping(value = "/answer")
public class AnswerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SavedAnswerDto> save(@RequestBody QuestionResponseDto questionResponseDto) {

        logger.info("Started saving an answer in database...");

        SavedAnswerDto savedAnswerDto = answerService.save(questionResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedAnswerDto);
    }
}
