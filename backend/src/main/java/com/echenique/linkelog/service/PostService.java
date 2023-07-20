package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.PostWithCommentsDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.models.Comment;
import com.echenique.linkelog.models.Post;
import com.echenique.linkelog.repositories.CommentRepositoryInterface;
import com.echenique.linkelog.repositories.PostRepositoryInteface;
import com.echenique.linkelog.repositories.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostService {
    @Autowired
    private PostRepositoryInteface postRepo;
    @Autowired
    private CommentRepositoryInterface commentRepo;
    @Autowired
    private UserRepositoryInterface userRepo;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    public PostWithCommentsDto getPostWithComments(int postId){
        Post post = postRepo.getPostById(postId);
        List<Comment> comments = commentRepo.getCommentsByPostId(postId);

        UserDto userDto = userService.getUserDtoById(post.getAutorId());
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comment comment : comments
             ) {
            commentsDto.add(commentService.getCommentDto(comment));
        }

        return new PostWithCommentsDto(userDto, commentsDto, post.getFechaPublicacion(), new ArrayList(), Integer.toString(post.getPostId()),post.getPostText(), post.getPostImgUrl());
    }
    public PostWithCommentsDto getPostWithComments(Post post){
        List<Comment> comments = commentRepo.getCommentsByPostId(post.getPostId());

        UserDto userDto = userService.getUserDtoById(post.getAutorId());
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comment comment : comments
        ) {
            commentsDto.add(commentService.getCommentDto(comment));
        }

        return new PostWithCommentsDto(userDto, commentsDto, post.getFechaPublicacion(), new ArrayList(), Integer.toString(post.getPostId()),post.getPostText(), post.getPostImgUrl());
    }
    public List<PostWithCommentsDto> getLastPostsWithComments(){
        List<Post> posts = postRepo.getAllPosts();
        List<PostWithCommentsDto> postWithCommentsDtos = new ArrayList<PostWithCommentsDto>();
        for (Post post:posts
             ) {
            postWithCommentsDtos.add(this.getPostWithComments(post));
        }
        return postWithCommentsDtos;
    }
    public void addNewPost(PostWithCommentsDto post){
       this.postRepo.addNewPost(post.getAutor().getUserId(), post.getText(), post.getImg(), post.getFechaPublicacion());
    }
    public List<PostWithCommentsDto> getPostsWithCommentsByPage(int page){
        List<PostWithCommentsDto> posts = new ArrayList<PostWithCommentsDto>();
        for (Post post:postRepo.getPostPageDecendentOrder(5, page)
             ) {
                    posts.add(this.getPostWithComments(post));
        }
        return posts;
    }
    public Post getPostById(int postId){
        return postRepo.getPostById(postId);
    }
}
