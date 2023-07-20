package com.echenique.linkelog.dto;

public class AddCommentDto {
    private String text;
    private int postId;
    private int authorId;

    public AddCommentDto() {
    }

    public AddCommentDto(String text, int postId, int authorId) {
        this.text = text;
        this.postId = postId;
        this.authorId = authorId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
