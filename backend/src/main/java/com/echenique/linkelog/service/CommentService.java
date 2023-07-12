package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.exception.CommentLimitReached;
import com.echenique.linkelog.exception.CommentNotFound;
import com.echenique.linkelog.models.Comment;
import com.echenique.linkelog.repositories.CommentRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
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

    public void addComment(CommentDto comment) throws CommentLimitReached{
       if(commentRepo.countComments(comment.getPostId()) < 20){
           commentRepo.addComment(comment.getPostId(),comment.getTexto(),comment.getAutor().getUserId(),comment.getFechaPublicacion());
       }else{
           throw new CommentLimitReached();
       }
    }

}
