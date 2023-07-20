package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comment;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface CommentRepositoryInterface {
    int addComment(int postId, String commentText, int authorId, Timestamp pubDate);
    Optional<Comment> getCommentById(int id);
    List<Comment> getCommentsByPostId(int id);
    int countComments(int postId);

    List<Comment> getCommentByUserId(int userId);

    int deleteComment(int commentId);
}
