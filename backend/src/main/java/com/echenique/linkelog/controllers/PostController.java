package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.AddPostDto;
import com.echenique.linkelog.dto.PostWithCommentsDto;
import com.echenique.linkelog.models.Post;
import com.echenique.linkelog.repositories.PostRepositoryInteface;
import com.echenique.linkelog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
public class PostController {
    @Autowired
    private PostRepositoryInteface postRepository;
    @Autowired
    private PostService postService;

    @GetMapping(value = "/post/{id}")
    public Post getPostById(@PathVariable int id){
        Post postToReturn = postRepository.getPostById(id);
        return  postToReturn;
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

    @GetMapping(value="/postPage/{page}")
    public List<PostWithCommentsDto> getPostPage(@PathVariable int page){
        return postService.getPostsWithCommentsByPageDesc(page);
        }

    @PostMapping(value="/post/add")
    public void addPost(@RequestBody AddPostDto post){
        this.postService.addNewPost(post);
    }


}
