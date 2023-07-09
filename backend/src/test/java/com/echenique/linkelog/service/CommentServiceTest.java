package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.models.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;

//@ExtendWith(SpringExtension.class)
@DataJdbcTest
@Sql("/init.sql")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CommentServiceTest {

    private Comment comment ;
    private UserDto user;
    private CommentDto commentDto;
    private Timestamp currentDate = new Timestamp(System.currentTimeMillis());
    @Autowired
    //private CommentService commentService;
    @BeforeEach
    private void setup(){
        //Set up comment to be used on test cases
        comment = new Comment();
        comment.setCommentId(1);
        comment.setPostId(1);
        comment.setAutor(1);
        comment.setTexto("hello test");
        comment.setFechaPublicacion(currentDate);
        //set User profile to be used on test case
        user = new UserDto();
        user.setUserId(1);
        //set up DTO to be used on test cases
        commentDto = new CommentDto();
        commentDto.setTexto("hello test");
        commentDto.setAutor(user);
        commentDto.setFechaPublicacion(currentDate);
        commentDto.setPostId(1);
        commentDto.setCommentId(1);
    }

    @Test
    @Disabled
    void getCommentDto() {
      //  assertEquals(commentDto, commentService.getCommentDto(comment));
    }

    @Test
    void getCommentDtoById() {
    }

    @Test
    void getCommentsByPostId() {
    }

    @Test
    void addComment() {
    }
}