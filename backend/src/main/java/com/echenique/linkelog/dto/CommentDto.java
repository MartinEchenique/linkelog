package com.echenique.linkelog.dto;

import java.sql.Timestamp;

public class CommentDto {

    private int commentId;
    private UserDto autor;
    private String texto;
    private Timestamp fechaPublicacion;
    private int postId;

    public CommentDto() {
    }

    public CommentDto(UserDto autor, String texto, Timestamp fechaPublicacion, int postId, int commentId) {
        this.autor = autor;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.postId = postId;
        this.commentId = commentId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
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

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
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
