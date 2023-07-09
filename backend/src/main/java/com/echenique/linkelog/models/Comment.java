package com.echenique.linkelog.models;

import java.sql.Timestamp;

public class Comment {

    private int autor;
    private String Texto;
    private Timestamp fechaPublicacion;
    private int commentId;
    private int postId;
    public Comment(){
    }

    public Comment(int autor, String texto, Timestamp fechaPublicacion, int commentId, int postId) {
        this.autor = autor;
        Texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.commentId = commentId;
        this.postId = postId;
    }

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

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
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
