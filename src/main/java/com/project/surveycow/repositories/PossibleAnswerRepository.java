package com.project.surveycow.repositories;

import com.project.surveycow.entities.PossibleAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {
}
