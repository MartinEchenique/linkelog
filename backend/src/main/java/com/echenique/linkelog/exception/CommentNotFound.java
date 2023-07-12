package com.echenique.linkelog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Comment not found")
public class CommentNotFound extends RuntimeException{
    public CommentNotFound(){
        super("Comment not found");
    }
}
