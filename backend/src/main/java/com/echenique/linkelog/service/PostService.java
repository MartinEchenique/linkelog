package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.CommentDto;
import com.echenique.linkelog.dto.PostDto;
import com.echenique.linkelog.dto.UserDto;
import com.echenique.linkelog.models.Comentario;
import com.echenique.linkelog.models.Post;
import com.echenique.linkelog.models.UserProfile;
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

    public PostDto getCompletePost(int postId){
        Post post = postRepo.getPostById(postId);
        List<Comentario> comments = commentRepo.getCommentsByPostId(postId);

        UserDto userDto = userService.getUserDtoById(post.getAutorId());
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comentario comentario: comments
             ) {
            commentsDto.add(commentService.getCommentDto(comentario));
        }

        return new PostDto(userDto, commentsDto, post.getFechaPublicacion(), new ArrayList(), Integer.toString(post.getPostId()),post.getPostText(), post.getPostImgUrl());
    }
    public PostDto getCompletePost(Post post){
        List<Comentario> comments = commentRepo.getCommentsByPostId(post.getPostId());

        UserDto userDto = userService.getUserDtoById(post.getAutorId());
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comentario comentario: comments
        ) {
            commentsDto.add(commentService.getCommentDto(comentario));
        }

        return new PostDto(userDto, commentsDto, post.getFechaPublicacion(), new ArrayList(), Integer.toString(post.getPostId()),post.getPostText(), post.getPostImgUrl());
    }
    public List<PostDto> getLastPosts(){
        List<Post> posts = postRepo.getAllPosts();
        List<PostDto> postDtos = new ArrayList<PostDto>();
        for (Post post:posts
             ) {
            postDtos.add(this.getCompletePost(post));
        }
        return postDtos;
    }
    public void addNewPost(PostDto post){
       this.postRepo.addNewPost(post.getAutor().getUserId(), post.getText(), post.getImg(), post.getFechaPublicacion());
    }
    public List<PostDto> getPostsPage(int page){
        List<PostDto> posts = new ArrayList<PostDto>();
        for (Post post:postRepo.getPostPageDecendentOrder(5, page)
             ) {
                    posts.add(this.getCompletePost(post));
        }
        return posts;
    }
}
