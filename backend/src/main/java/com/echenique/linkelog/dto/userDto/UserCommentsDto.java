package com.echenique.linkelog.dto.userDto;

import com.echenique.linkelog.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class UserCommentsDto {
    private UserDto user;
    private List<Comment> commentList;

    public UserCommentsDto() {
        commentList = new ArrayList<>();
    }

    public UserCommentsDto(UserDto user, List<Comment> commentList) {
        this.user = user;
        this.commentList = commentList;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void addCommet(Comment comment){
        commentList.add(comment);
    }
}
