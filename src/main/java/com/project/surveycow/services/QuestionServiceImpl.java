package com.project.surveycow.services;

import com.project.surveycow.dtos.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Override
    public QuestionDto save(QuestionDto questionDto) {
        return null;
    }

    @Override
    public Page<QuestionDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
