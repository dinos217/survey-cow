package com.project.surveycow.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "answer")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guestion_id")
    private Long questionId;

    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "user_id")
    private Long userId;
}
