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