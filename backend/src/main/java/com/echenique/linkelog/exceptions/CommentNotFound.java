package com.echenique.linkelog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Comment not found")
public class CommentNotFound extends RuntimeException{
    public CommentNotFound(){
        super("Comment not found");
    }
}
