package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.commentDto.CommentDto;
import com.echenique.linkelog.dto.postDto.AddPostDto;
import com.echenique.linkelog.dto.postDto.EditPostDto;
import com.echenique.linkelog.dto.postDto.PostDto;
import com.echenique.linkelog.dto.postDto.PostWithCommentsDto;
import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.models.Post;
import com.echenique.linkelog.repositories.PostRepositoryInteface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepositoryInteface postRepo;
    private final CommentService commentService;
    private final UserService userService;
    private final int DEFAULT_PAGE_SIZE = 5;
    public PostService(PostRepositoryInteface postRepo, CommentService commentService, UserService userService) {
        this.postRepo = postRepo;
        this.commentService = commentService;
        this.userService = userService;
    }

    public PostWithCommentsDto getPostWithCommentsById(int postId){
        Post post = postRepo.getPostById(postId);
        return this.getPostWithCommentsById(post);
    }
    public PostWithCommentsDto getPostWithCommentsById(Post post){
        List<CommentDto> comments = commentService.getCommentsByPostId(post.getPostId());
        UserDto userDto = userService.getUserDtoById(post.getAutorId());
        return new PostWithCommentsDto(userDto, comments, post.getFechaPublicacion(), new ArrayList(), post.getPostId(),post.getPostText(), post.getPostImgUrl());
    }
    public List<PostWithCommentsDto> getAllPostsWithComments(){
        List<Post> posts = postRepo.getAllPosts();
        List<PostWithCommentsDto> postWithCommentsDtos = posts.stream()
                                                        .map(post -> this.getPostWithCommentsById(post))
                                                        .toList();
        return postWithCommentsDtos;
    }
    public void addNewPost(AddPostDto post){
        Timestamp postDate = new Timestamp(System.currentTimeMillis());
        postRepo.addNewPost(post.getAuthorId(), post.getText(), post.getImg(), postDate);
    }
    public List<PostWithCommentsDto> getPostsWithCommentsByPageDesc(int page){
        List<PostWithCommentsDto> posts = postRepo.getPostPageDescDate(DEFAULT_PAGE_SIZE, page)
                                                    .stream()
                                                    .map(post -> getPostWithCommentsById(post))
                                                    .toList();
        return posts;
    }
    public List<PostWithCommentsDto> getPostsWithCommentsByPageDesc(int page, int pageSize){
        List<PostWithCommentsDto> posts = postRepo.getPostPageDescDate(pageSize, page)
                .stream()
                .map(post -> getPostWithCommentsById(post))
                .toList();
        return posts;
    }
    public PostDto getPostById(int postId){
        Post post = postRepo.getPostById(postId);
        int numberOfComments = commentService.getNumberOfCommentByPostId(post.getPostId());
        return new PostDto(post.getAutorId(), post.getPostId(),
                post.getPostText(), post.getPostImgUrl(),
                post.getFechaPublicacion(),numberOfComments);
    }

    public void deletePost(int id) {
        postRepo.deletePost(id);
    }

    public void editPost(EditPostDto editInfo) {
        int postId = editInfo.getPostId();
        String editText = editInfo.getText();
        String editImg = editInfo.getImg();
        if (editImg != null && editInfo != null) postRepo.editPost(editText,editImg, postId);
        else if(editImg != null){
            postRepo.editPostImg(editImg, postId);
        }
        else if(editText != null){
            postRepo.editPostText(editText, postId);
        }
    }

    public List<PostWithCommentsDto> getPostByUserId(int userId) {
        return postRepo.getPostsByUserId(userId)
                .stream()
                .map(post -> getPostWithCommentsById(post))
                .toList();
    }
}
