package com.echenique.linkelog.dto;

import java.sql.Timestamp;

public class PostDto {
    private int autorId;
    private int postId;
    private String postText;
    private String postImgUrl;
    private Timestamp fechaPublicacion;
    private int amountOfComments;
    public PostDto() {
    }

    public PostDto(int autorId, int postId, String postText, String postImgUrl, Timestamp fechaPublicacion, int amountOfComments) {
        this.autorId = autorId;
        this.postId = postId;
        this.postText = postText;
        this.postImgUrl = postImgUrl;
        this.fechaPublicacion = fechaPublicacion;
        this.amountOfComments = amountOfComments;

    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public void setPostImgUrl(String postImgUrl) {
        this.postImgUrl = postImgUrl;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getAmountOfComments() {
        return amountOfComments;
    }

    public void setAmountOfComments(int amountOfComments) {
        this.amountOfComments = amountOfComments;
    }
}
