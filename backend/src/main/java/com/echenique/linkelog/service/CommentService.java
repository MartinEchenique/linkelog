package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.models.Comentario;
import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.CommentRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    UserService userService;
    @Autowired
    CommentRepositoryInterface commentRepo;
    public CommentDto getCommentDto( Comentario comentario){
        UserDto user = userService.getUserDtoById(comentario.getAutor());
        return new CommentDto(user, comentario.getTexto(), comentario.getFechaPublicacion(), comentario.getPostId());
    }

    public CommentDto getCommentDtoById(int id){
        Comentario comment = commentRepo.getCommentById(id);
        CommentDto commentDto = this.getCommentDto(comment);
        return commentDto;
    }

    public List<CommentDto> getCommentsByPostId(int postId){
        List<CommentDto> commentList = new ArrayList<CommentDto>();
        for (Comentario comment: commentRepo.getCommentsByPostId(postId))
        {
                      commentList.add(this.getCommentDto(comment));
        }
        return commentList;
    }

    public void addComment(CommentDto comment){
        commentRepo.addComment(comment.getPostId(),comment.getTexto(),comment.getAutor().getUserId(),comment.getFechaPublicacion());
    }

}
