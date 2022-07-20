package com.project.surveycow.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SurveyAlreadyTakenException extends RuntimeException {

    public SurveyAlreadyTakenException(String message) {
        super(message);
    }

}
