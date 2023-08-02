package com.echenique.linkelog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "validation failed")
public class FailedValidationException extends RuntimeException{
    public FailedValidationException(String message){
        super(message);
    }
}
