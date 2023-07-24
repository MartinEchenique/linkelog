package com.echenique.linkelog.dto.commentDto;

public class EditCommentDto {
    private String text;
    private int commentId;

    public EditCommentDto() {
    }

    public EditCommentDto(String text, int commentId) {
        this.text = text;
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
