package com.project.surveycow.services;

import com.project.surveycow.dtos.AnswerDto;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {

    AnswerDto save(AnswerDto answerDto);


}
