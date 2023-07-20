package com.echenique.linkelog.dto;

import java.sql.Timestamp;
import java.util.List;

public class PostWithCommentsDto {
    UserDto autor;
    List<CommentDto> comentarios;
    Timestamp fechaPublicacion;
    List reacciones;
    String id;
    String text;
    String img;

    public PostWithCommentsDto() {
    }

    public PostWithCommentsDto(UserDto autor, List<CommentDto> comentarios, Timestamp fechaPublicacion, List reacciones, String id, String text, String img) {
        this.autor = autor;
        this.comentarios = comentarios;
        this.fechaPublicacion = fechaPublicacion;
        this.reacciones = reacciones;
        this.id = id;
        this.text = text;
        this.img = img;
    }

    public UserDto getAutor() {
        return autor;
    }

    public void setAutor(UserDto autor) {
        this.autor = autor;
    }

    public List<CommentDto> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<CommentDto> comentarios) {
        this.comentarios = comentarios;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public List getReacciones() {
        return reacciones;
    }

    public void setReacciones(List reacciones) {
        this.reacciones = reacciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
