package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.AddCommentDto;
import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.UserCommentsDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.exceptions.CommentLimitReached;
import com.echenique.linkelog.exceptions.CommentNotFound;
import com.echenique.linkelog.models.Comment;
import com.echenique.linkelog.repositories.CommentRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private static final int COMMENT_LIMIT = 20;
    @Autowired
    UserService userService;
    @Autowired
    CommentRepositoryInterface commentRepo;
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

    public void addComment(AddCommentDto comment) throws CommentLimitReached{
       if(commentRepo.countComments(comment.getPostId()) < COMMENT_LIMIT){
           commentRepo.addComment(comment.getPostId(),comment.getTexto(),comment.getAutorId(),comment.getFechaPublicacion());
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
