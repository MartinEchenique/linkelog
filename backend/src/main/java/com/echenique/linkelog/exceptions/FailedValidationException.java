package com.echenique.linkelog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Comment not found")
public class FailedValidationException extends RuntimeException{
    public FailedValidationException(){
        super("Comment not found");
    }
}
