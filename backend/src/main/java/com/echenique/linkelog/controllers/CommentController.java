package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.AddCommentDto;
import com.echenique.linkelog.dto.CommentDto;

import com.echenique.linkelog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@RequestMapping("/comments")
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping(path ="/post/{id}")
    public List<CommentDto> getCommentsByPostId(@PathVariable int id){
        return commentService.getCommentsByPostId(id);
    }
    @GetMapping(path ="/comment/{id}")
    public CommentDto getCommentsById(@PathVariable int id){
        return commentService.getCommentDtoById(id);
    }

    @PostMapping(path="/add")
    public void addComment(@RequestBody AddCommentDto comment){
        commentService.addComment(comment);
    }
    @DeleteMapping(path="/remove/{id}")
    public void deleteComment(@PathVariable int id){
        commentService.deleteComment(id);
    }
}
