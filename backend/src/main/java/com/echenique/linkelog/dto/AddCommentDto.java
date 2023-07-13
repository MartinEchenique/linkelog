package com.echenique.linkelog.dto;

import java.sql.Timestamp;

public class AddCommentDto {
    private int autorId;
    private String texto;
    private Timestamp fechaPublicacion;
    private int postId;

    public AddCommentDto() {
    }

    public AddCommentDto(int autorId, String texto, Timestamp fechaPublicacion, int postId) {
        this.autorId = autorId;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.postId = postId;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
