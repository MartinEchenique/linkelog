package com.models;

import java.sql.Date;

public class Comentario {

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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

    private int autor;
    private String Texto;
    private Date fechaPublicacion;
    private int commentId;
    private int postId;

    @Override
    public String toString() {
        return "Comentario{" +
                "autor=" + autor +
                ", Texto='" + Texto + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", commentId=" + commentId +
                ", postId=" + postId +
                '}';
    }
}
