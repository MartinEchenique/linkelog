package com.echenique.linkelog.dto;

import java.sql.Timestamp;

public class AddCommentDto {
    private String texto;
    private int postId;

    public AddCommentDto() {
    }

    public AddCommentDto(String texto, int postId) {
        this.texto = texto;
        this.postId = postId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
