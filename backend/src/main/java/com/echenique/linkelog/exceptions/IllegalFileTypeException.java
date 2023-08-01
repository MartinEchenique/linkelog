package com.echenique.linkelog.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Media type is not supported")
public class IllegalFileTypeException extends RuntimeException{
    public IllegalFileTypeException(String message){
        super(message);
    }
}
