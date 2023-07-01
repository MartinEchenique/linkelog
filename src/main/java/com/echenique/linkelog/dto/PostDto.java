package com.echenique.linkelog.dto;

import java.sql.Date;
import java.util.List;

public class PostDto {
    UserDto autor;
    List<CommentDto> comentarios;
    Date fechaPublicacion;
    List reacciones;
    String id;
    String text;
    String img;

    public PostDto() {
    }

    public PostDto(UserDto autor, List<CommentDto> comentarios, Date fechaPublicacion, List reacciones, String id, String text, String img) {
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

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
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
