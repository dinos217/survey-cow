package com.project.surveycow.controllers;

import com.project.surveycow.dtos.SurveyDto;
import com.project.surveycow.dtos.SurveyRequestDto;
import com.project.surveycow.services.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/all")
    public ResponseEntity<Page<SurveyDto>> findAll(@RequestParam Integer page,
                                                   @RequestParam Integer pageSize,
                                                   @RequestParam String sortBy,
                                                   @RequestParam String direction) {

        logger.info("Started finding all surveys paged...");

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return ResponseEntity.status(HttpStatus.OK).body(surveyService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SurveyDto> findById(@PathVariable Long id) {

        logger.info("Started finding survey with id: " + id);

        return ResponseEntity.status(HttpStatus.OK).body(surveyService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        logger.info("Started deleting survey with id: " + id);

        surveyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
