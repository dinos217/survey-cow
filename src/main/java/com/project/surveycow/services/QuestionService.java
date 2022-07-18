package com.project.surveycow.services;

import com.project.surveycow.dtos.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {

    QuestionDto save(QuestionDto questionDto);

    Page<QuestionDto> findAll(Pageable pageable);

    void delete(Long id);

}
