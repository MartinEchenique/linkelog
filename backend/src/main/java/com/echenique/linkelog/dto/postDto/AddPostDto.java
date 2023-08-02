package com.echenique.linkelog.dto.postDto;


public class AddPostDto {
    private int authorId;
    private String text;
    private String img;

    public AddPostDto() {
    }

    public AddPostDto(int authorId, String text, String img) {
        this.authorId = authorId;
        this.text = text;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
