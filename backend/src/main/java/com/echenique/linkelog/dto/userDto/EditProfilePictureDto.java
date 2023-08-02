package com.echenique.linkelog.dto.userDto;


public class EditProfilePictureDto {
    private Integer x;
    private Integer y;
    private Integer size;

    public EditProfilePictureDto() {
    }

    public EditProfilePictureDto(Integer x, Integer y, Integer size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

