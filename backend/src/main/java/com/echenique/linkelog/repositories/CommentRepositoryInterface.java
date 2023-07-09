package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comment;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

public interface CommentRepositoryInterface {
    public int addComment(int postId, String commentText, int authorId, Timestamp pubDate);
    public Comment getCommentById(int id);

    public List<Comment> getCommentsByPostId(int id);
}
