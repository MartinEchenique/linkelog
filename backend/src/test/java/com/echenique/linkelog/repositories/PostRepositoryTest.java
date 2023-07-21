package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql("/init.sql")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PostRepositoryTest {

    @Autowired
    PostRepository postRepo;
    @Test
    @Sql("/init_no_post.sql")
    @DisplayName("Add new post")
    public void postRepository_AddNewPost_postIsAdded(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        postRepo.addNewPost(1,"new post", "url", now);
        Post addedPost = postRepo.getPostById(1);
        assertEquals("new post",addedPost.getPostText());
        assertEquals("url", addedPost.getPostImgUrl());
        assertEquals(now.toString(), addedPost.getFechaPublicacion().toString());
        assertEquals(1, addedPost.getAutorId());
    }

    @Test
    @DisplayName("Get post by id")
    public void postRepository_getPostById_returnPost(){
        Post post = postRepo.getPostById(1);
        assertEquals("Post 1",post.getPostText());
        assertEquals("img_url", post.getPostImgUrl());
        assertEquals("2000-01-01 00:00:01.0", post.getFechaPublicacion().toString());
        assertEquals(1, post.getAutorId());
    }
    @Test
    @DisplayName("get posts by user Id")
    public void postRepository_getPostsByUserId_returnUserPostList(){
        List<Post> postList = postRepo.getPostsByUserId(1);
        assertEquals(3, postList.size());
    }
    @Test
    @DisplayName("get all posts")
    public void postRepository_getAllPosts_returnAllUserPostList(){
        List<Post> postList = postRepo.getAllPosts();
        assertEquals(6,postList.size());
    }

    @Test
    @DisplayName("get post page desc order")
    public void postRepository_getPostPageDescDate_returnPostPage(){
        List<Post> postList = postRepo.getPostPageDescDate(2,0);
        assertEquals(2,postList.size());
        assertEquals(6,postList.get(0).getPostId());
        assertEquals(5,postList.get(1).getPostId());
    }

    @Test
    @DisplayName("get post page asc order")
    public void postRepository_getPostPageAscDate(){
        List<Post> postList = postRepo.getPostPageAscDate(2,0);
        assertEquals(2, postList.size());
        assertEquals(1, postList.get(0).getPostId());
        assertEquals(2, postList.get(1).getPostId());
    }

}