package com.echenique.linkelog.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

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

    @Sql("/init.sql")
    @Test
    public void commentController_getCommentsByPostId_returnComments() throws Exception {

        mockMvc.perform(get("/comments/post/{id}", 3)).andExpect(jsonPath("$.length()").value(3));

    }
    @Sql("/init.sql")
    @Test
    public void commentController_getCommentsByPostId_postNotFound() throws Exception {

        mockMvc.perform(get("/comments/post/{id}", 1)).andExpect(status().isNotFound());
    }
}