package com.echenique.linkelog.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "coordinates out of range")
public class CoordinatesOutOfRangeException extends RuntimeException{
    public CoordinatesOutOfRangeException(String message){
        super(message);
    }
}
