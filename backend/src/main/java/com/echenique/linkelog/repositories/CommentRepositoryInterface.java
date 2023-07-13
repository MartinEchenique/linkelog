package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comment;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface CommentRepositoryInterface {
    public int addComment(int postId, String commentText, int authorId, Timestamp pubDate);
    public Optional<Comment> getCommentById(int id);
    public List<Comment> getCommentsByPostId(int id);
    public int countComments(int postId);

    List<Comment> getCommentByUserId(int userId);

    int deleteComment(int commentId);
}
