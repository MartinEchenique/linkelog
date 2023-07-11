package com.echenique.linkelog.service;


import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.models.Comment;
import com.echenique.linkelog.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@SpringBootTest
class CommentServiceTest {

    @Mock
    UserService userService;
    @Mock
    CommentRepository commentRepo;
    @InjectMocks
    CommentService commentService;
    @Test
    public void commentService_getCommentDto_returnsDtoFromComment(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(1,"comment text", now ,1,1);

        UserDto user = new UserDto();

        when(userService.getUserDtoById(1)).thenReturn(user);

        CommentDto commentDto= commentService.getCommentDto(comment);
        assertEquals(user,commentDto.getAutor(), "Not the same author");
        assertEquals("comment text", commentDto.getTexto(), "Comment text isn't correct");
        assertEquals(now.toString(), commentDto.getFechaPublicacion().toString(), "Timestamp isn't the same");
        assertEquals(1,commentDto.getPostId(), "Incorrect post ID");
        assertEquals(1,commentDto.getCommentId(), "Incorrect comment ID");
    }

    @Test
    public void commentService_getCommentDtoById_returnsDtoFromId(){
        UserDto user = new UserDto();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(1,"comment text", now ,1,1);
        CommentDto dto = new CommentDto(user, "comment text", now, 1,1);

        CommentService commentService1 = spy(commentService);


        when(commentRepo.getCommentById(1)).thenReturn(comment);
        when(commentService1.getCommentDto(comment)).thenReturn(dto);

        CommentDto commentDto= commentService1.getCommentDtoById(1);

        assertEquals(dto, commentDto);
    }

    @Test
    public void commentService_getCommentsByPostId(){
        UserDto user = new UserDto();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(1,"comment text", now ,1,1);
        Comment comment1 = new Comment(1,"comment text", now ,2,1);
        CommentDto dto = new CommentDto(user, "comment text", now, 1,1);
        CommentDto dto1 = new CommentDto(user, "comment text", now, 1,1);

        CommentService commentService1 = spy(commentService);

        when(commentRepo.getCommentsByPostId(1)).thenReturn(List.of(comment,comment1));
        when(commentService1.getCommentDto(comment)).thenReturn(dto);
        when(commentService1.getCommentDto(comment1)).thenReturn(dto1);

        List<CommentDto> comments = commentService1.getCommentsByPostId(1);

        assertEquals(2, comments.size());
        assertEquals(dto, comments.get(0));
        assertEquals(dto1, comments.get(1));

    }
}