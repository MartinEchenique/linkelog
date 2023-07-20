package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.AddCommentDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql("/init.sql")
@AutoConfigureMockMvc
@SpringBootTest
class CommentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Disabled
    @Test
    public void commentController_getCommentsByPostId_returnComments() throws Exception {
        mockMvc.perform(get("/comments/post/{id}", 3))
                .andExpect(jsonPath("$.length()").value(3));
    }
    @Disabled
    @Test
    public void commentController_getCommentsByPostId_postNotFoundReturnEmpty() throws Exception {
        mockMvc.perform(get("/comments/post/{id}", 100))
                .andExpect(jsonPath("$.length()").value(0));
    }
    @Disabled
    @Test
    public void commentController_getCommentById_returnCommentDto() throws Exception {
        mockMvc.perform(get("/comments/comment/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(1))
                .andExpect(jsonPath("$.autor.userId").value(1));
    }
    @Disabled
    @Test
    public void commentController_getCommentById_returnNotFoundStatus() throws Exception {
        mockMvc.perform(get("/comments/comment/{id}", -1))
                .andExpect(status().isNotFound());
    }
    @Disabled
    @Sql("/init_no_comments.sql")
    @Test
    public void commentController_postComment_returnOkStatus() throws Exception {
        ObjectMapper om = new ObjectMapper();
        AddCommentDto comment = new AddCommentDto( "comment text",  1,10);

        mockMvc.perform(
                        post("/comments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(comment))
        ).andExpect(status().isOk());

        mockMvc.perform(get("/comments/comment/{id}", 1))
                .andExpect(status().isOk());

    }

    @Disabled
    @Test
    public void commentController_deleteComment_commentIsDeleted() throws Exception {
        mockMvc.perform(delete("/comments/remove/{id}",1))
                .andExpect(status().isOk());
        mockMvc.perform(get("/comments/comment/{id}",1))
                .andExpect(status().isNotFound());
    }
    public void commentController_deleteComment_commentAlreadyDeleted() throws Exception {
        mockMvc.perform(delete("/comments/remove/{id}",-1))
                .andExpect(status().isOk());
    }
}