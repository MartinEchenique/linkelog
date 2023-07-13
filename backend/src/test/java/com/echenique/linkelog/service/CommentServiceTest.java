package com.echenique.linkelog.service;


import com.echenique.linkelog.dto.AddCommentDto;
import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.UserCommentsDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.exceptions.CommentLimitReached;
import com.echenique.linkelog.exceptions.CommentNotFound;
import com.echenique.linkelog.models.Comment;
import com.echenique.linkelog.repositories.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class CommentServiceTest {

    @MockBean
    private UserService userService;
    @MockBean
    private CommentRepository commentRepo;

    @Autowired
    private CommentService commentService;

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
        Optional<Comment> optionalComment = Optional.of(comment);
        CommentDto dto = new CommentDto(user, "comment text", now, 1,1);

        CommentService commentService1 = spy(commentService);


        when(commentRepo.getCommentById(1)).thenReturn(optionalComment);
        when(commentService1.getCommentDto(comment)).thenReturn(dto);

        CommentDto commentDto= commentService1.getCommentDtoById(1);

        assertEquals(dto, commentDto);
    }
    @Test
    public void commentService_getCommentDtoById_throwsIfNotFound(){
        Optional<Comment> optionalComment = Optional.empty();

        when(commentRepo.getCommentById(1)).thenReturn(optionalComment);
        assertThrows(CommentNotFound.class,() ->  commentService.getCommentDtoById(1));
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
    @Test
    public void commentService_addComment_throwsCommentLimitReached(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AddCommentDto dto = new AddCommentDto(1,"comment text", now,4);

        when(commentRepo.countComments(4)).thenReturn(20);
        assertThrows(CommentLimitReached.class,() -> commentService.addComment(dto));
    }
    @Test
    public void commentService_addComment_callsRepositoryAddComment(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AddCommentDto dto = new AddCommentDto(1,"comment text", now,4);

        when(commentRepo.countComments(4)).thenReturn(19);
        commentService.addComment(dto);
        verify(commentRepo, times(1)).addComment(4,"comment text", 1, now);
    }
    @Test
    @DisplayName("Get comment by user id - 3 comments")
    public void commentService_getCommentsByUserId_returnCommentsDto(){
        List<Comment> comments = List.of(new Comment(), new Comment(), new Comment());
        UserDto user = new UserDto();

        when(commentRepo.getCommentByUserId(1)).thenReturn(comments);
        when(userService.getUserDtoById(1)).thenReturn(user);

        UserCommentsDto userComments = commentService.getUserCommentDtoById(1);

        assertEquals(comments, userComments.getCommentList());
        assertEquals(user, userComments.getUser());
    }
    @Test
    @DisplayName("Get comment by user id - no comments")
    public void commentService_getCommentsByUserId_ThrowsUserNotFound(){
        List<Comment> comments = new ArrayList<>();
        UserDto user = new UserDto();

        when(commentRepo.getCommentByUserId(1)).thenReturn(comments);
        when(userService.getUserDtoById(1)).thenReturn(user);

        UserCommentsDto userComments = commentService.getUserCommentDtoById(1);

        assertEquals(0, userComments.getCommentList().size());
        assertEquals(user, userComments.getUser());
    }
    @Test
    @DisplayName("Delete comment")
    public void commentService_deleteComment_callsRepositoryDeleteComment(){
        commentService.deleteComment(1);
        verify(commentRepo, times(1)).deleteComment(1);
    }
}
