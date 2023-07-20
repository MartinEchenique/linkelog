package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.AddCommentDto;
import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.UserCommentsDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.exceptions.CommentLimitReached;
import com.echenique.linkelog.exceptions.CommentNotFound;
import com.echenique.linkelog.models.Comment;
import com.echenique.linkelog.repositories.CommentRepositoryInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private static final int COMMENT_LIMIT = 20;
    private final UserService  userService;
    private final CommentRepositoryInterface commentRepo;
    public CommentService(UserService userService, CommentRepositoryInterface commentRepo){
        this.userService = userService;
        this.commentRepo = commentRepo;
    }

    public CommentDto getCommentDto(Comment comment){
        UserDto user = userService.getUserDtoById(comment.getAutor());
        return new CommentDto(user, comment.getTexto(), comment.getFechaPublicacion(), comment.getPostId(), comment.getCommentId());
    }

    public CommentDto getCommentDtoById(int id) {
        Comment comment = commentRepo.getCommentById(id).orElseThrow(CommentNotFound::new);
        return this.getCommentDto(comment);
    }

    public List<CommentDto> getCommentsByPostId(int postId){
        List<CommentDto> commentList = new ArrayList<CommentDto>();
        for (Comment comment: commentRepo.getCommentsByPostId(postId))
        {
                      commentList.add(this.getCommentDto(comment));
        }
        return commentList;
    }
    @Transactional
    public void addComment(AddCommentDto comment, int authorId) throws CommentLimitReached{
        Timestamp commentDate = new Timestamp(System.currentTimeMillis());
        if(commentRepo.countComments(comment.getPostId()) < COMMENT_LIMIT){
           commentRepo.addComment(comment.getPostId(),comment.getTexto(), authorId,commentDate);
       }else{
           throw new CommentLimitReached();
       }
    }

    public UserCommentsDto getUserCommentDtoById(int userId) {
        UserDto user = userService.getUserDtoById(userId);
        List<Comment> comments = commentRepo.getCommentByUserId(userId);
        return new UserCommentsDto(user,comments);
    }

    public void deleteComment(int commentId) {
        commentRepo.deleteComment(commentId);
    }
}
