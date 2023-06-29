package com.models;

public class Post {
    private int autorId;
    private int postId;
    private String postText;
    private String postImgUrl;

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
