package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.postDto.*;
import com.echenique.linkelog.security.LoggedUserHelper;
import com.echenique.linkelog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@RequestMapping("/posts")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/post/{id}")
    public PostDto getPostById(@PathVariable int id){

        return   postService.getPostById(id);
    }

    @GetMapping(value = "/postComplete/{id}")
    public PostWithCommentsDto getCompletePostById(@PathVariable int id){
        PostWithCommentsDto postToReturn = postService.getPostWithCommentsById(id);
        return postToReturn;
    }

    @GetMapping(value="/allPosts")
    public  List<PostWithCommentsDto> getAllPost(){
        List<PostWithCommentsDto> posts = postService.getAllPostsWithComments();
        return posts;
    }

    @GetMapping(value="/allPostsPage/{page}")
    public List<PostWithCommentsDto> getPostPage(@PathVariable int page){
        return postService.getPostsWithCommentsByPageDesc(page);
        }
    @GetMapping(value="/user/{userId}")
    public List<PostWithCommentsDto> getPostByUser(@PathVariable int userId){
        return postService.getPostByUserId(userId);
    }

    @PostMapping(value="/add")
    public void addPost(@RequestBody AddPostDto post){
        int postUserId = LoggedUserHelper.getLoggedId();
        post.setAuthorId(postUserId);
        this.postService.addNewPost(post);
    }
    @PreAuthorize("@postAuthorizeCondition.loggedOwnsPost(#id)")
    @DeleteMapping(value="/delete/{id}")
    public void deletePost(@PathVariable int id){
        postService.deletePost(id);
    }

    @PreAuthorize("@postAuthorizeCondition.loggedOwnsPost(#editInfo.getPostId())")
    @PutMapping(value="/editPost")
    public void editPostImg(@RequestBody EditPostDto editInfo){
        postService.editPost(editInfo);
    }


}
