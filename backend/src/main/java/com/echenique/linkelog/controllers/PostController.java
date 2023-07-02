package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.PostDto;
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
    public PostDto getCompletePostById(@PathVariable int id){
        PostDto postToReturn = postService.getCompletePost(id);
        return postToReturn;
    }

    @GetMapping(value="/allPosts")
    public  List<PostDto> getAllPost(){
        List<PostDto> posts = postService.getLastPosts();
        return posts;
    }

    @PostMapping(value="/post/add")
    public void addPost(@RequestBody PostDto post){
        this.postService.addNewPost(post);
    }


}
