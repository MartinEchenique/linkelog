package com.echenique.linkelog.dto.postDto;

public class EditPostDto {
    private String img;
    private int postId;
    private String text;
    public EditPostDto() {
    }

    public EditPostDto(String img, String text,int postId) {
        this.img = img;
        this.text = text;
        this.postId = postId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
