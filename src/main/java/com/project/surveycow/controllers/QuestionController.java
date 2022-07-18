package com.project.surveycow.controllers;

import com.project.surveycow.dtos.QuestionDto;
import com.project.surveycow.services.QuestionService;
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
@RequestMapping(value = "/question")
public class QuestionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/all")
    Page<QuestionDto> findAll(@RequestParam Integer page,
                              @RequestParam Integer pageSize,
                              @RequestParam String sortBy,
                              @RequestParam String direction) {

        logger.info("Started finding all questions paged...");

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return questionService.findAll(pageable);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<QuestionDto> save(@RequestBody QuestionDto questionDto) {

        logger.info("Started saving question in database...");

        QuestionDto gameDto = questionService.save(questionDto);
        return ResponseEntity.status(HttpStatus.OK).body(gameDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        logger.info("Started deleting question from database...");

        questionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
