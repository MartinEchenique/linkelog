package com.echenique.linkelog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Comment limit for this post reached")
public class CommentLimitReached extends RuntimeException{
    public CommentLimitReached(){
        super("Comment limit for post reached");
    }
}
