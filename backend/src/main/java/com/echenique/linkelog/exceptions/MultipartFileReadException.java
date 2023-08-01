package com.echenique.linkelog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Failed to read file")
public class MultipartFileReadException extends RuntimeException{
    public MultipartFileReadException(String message){
        super(message);
    }
}
