package com.project.surveycow.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "answer")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "guestion_id")
    private Long questionId;

    @Column(name = "user_response")
    private String userResponse;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
