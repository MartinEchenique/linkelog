package com.echenique.linkelog.models;

import java.sql.Timestamp;

public class Post {
    private int autorId;
    private int postId;
    private String postText;
    private String postImgUrl;
    private Timestamp fechaPublicacion;

    public Post(int autorId, int postId, String postText, String postImgUrl, Timestamp fechaPublicacion) {
        this.autorId = autorId;
        this.postId = postId;
        this.postText = postText;
        this.postImgUrl = postImgUrl;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Post() {
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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

    @Override
    public String toString() {
        return "Post{" +
                "autorId=" + autorId +
                ", postId=" + postId +
                ", postText='" + postText + '\'' +
                ", postImgUrl='" + postImgUrl + '\'' +
                '}';
    }
}
