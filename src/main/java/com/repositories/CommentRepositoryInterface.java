package com.repositories;

import com.models.Comentario;

import java.sql.Date;
import java.util.List;

public interface CommentRepositoryInterface {
    public void addComment(int postId, String commentText, int authorId, Date pubDate);
    public Comentario getCommentById(int id);

    public List<Comentario> getCommentsByPostId(int id);
}
