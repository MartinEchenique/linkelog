package com.echenique.linkelog.dto;

import java.sql.Date;

public class CommentDto {
    public CommentDto() {
    }
    private UserDto autor;
    private String texto;
    private Date fechaPublicacion;
    private int postId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public CommentDto(UserDto autor, String texto, Date fechaPublicacion, int postId) {
        this.autor = autor;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.postId = postId;

    }

    public UserDto getAutor() {
        return autor;
    }

    public void setAutor(UserDto autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "autor=" + autor +
                ", texto='" + texto + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
