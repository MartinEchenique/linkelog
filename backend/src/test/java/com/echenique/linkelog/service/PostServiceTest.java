package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.*;
import com.echenique.linkelog.models.Post;
import com.echenique.linkelog.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostServiceTest {

    @MockBean
    private PostRepository postRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private CommentService commentService;
    @Autowired
    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("get post with comments by post")
    public void postService_getPostWithComments_returnPostWithComments(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        UserDto author = new UserDto();
        List<CommentDto> comments= List.of(new CommentDto(), new CommentDto());
        Post postToGet = new Post(1, 1, "", "", now);

        when(commentService.getCommentsByPostId(1)).thenReturn(comments);
        when(userService.getUserDtoById(1)).thenReturn(author);

        PostWithCommentsDto postWithComments = postService.getPostWithCommentsById(postToGet);
        assertEquals(comments, postWithComments.getComentarios());
        assertEquals(author, postWithComments.getAutor());
        assertEquals(now, postWithComments.getFechaPublicacion());
        assertEquals(1, postWithComments.getId());
    }

    @Test
    @DisplayName("Get all posts with comments")
    public void postService_getAllPostWithComments_allPostList(){
        Post post1 = new Post();
        Post post2 = new Post();
        PostWithCommentsDto postDto1 = new PostWithCommentsDto();
        PostWithCommentsDto postDto2 = new PostWithCommentsDto();

        List<Post> postList = List.of(post1, post2);
        PostService postServiceMock = spy(new PostService(postRepository, commentService, userService ));

        when(postServiceMock.getPostWithCommentsById(post1)).thenReturn(postDto1);
        when(postServiceMock.getPostWithCommentsById(post2)).thenReturn(postDto2);
        when(postRepository.getAllPosts()).thenReturn(postList);
        List<PostWithCommentsDto> resultList = postServiceMock.getAllPostsWithComments();

        assertEquals(2, resultList.size());
        assertEquals(postDto1, resultList.get(0));
        assertEquals(postDto2, resultList.get(1));

    }

    @Test
    @DisplayName("add new post")
    public void postService_addPost_callsRepoAddPost(){
        AddPostDto postToAdd = new AddPostDto(1, "post", "img");

        postService.addNewPost(postToAdd);
        verify(postRepository, times(1)).addNewPost(eq(1),eq("post"),eq("img"),any(Timestamp.class));

    }

    @Test
    @DisplayName("Get posts by page desc default size")
    public void postService_getPostByPageDesc_getPostPage(){
        List<Post> repositoryResponse = List.of(new Post(), new Post(), new Post(), new Post(), new Post());
        PostService postServiceMock = spy(new PostService(postRepository, commentService, userService ));
        doReturn(new PostWithCommentsDto()).when(postServiceMock).getPostWithCommentsById(any(Post.class));
        when(postRepository.getPostPageDescDate(5,0)).thenReturn(repositoryResponse);
        List<PostWithCommentsDto> page = postService.getPostsWithCommentsByPageDesc(0);
        assertEquals(5, page.size());
    }
    @Test
    @DisplayName("Get posts by page desc custom size")
    public void postService_getPostByPageDescCustomSize_getPostPage(){
        List<Post> repositoryResponse = List.of(new Post(), new Post(), new Post(),
                new Post(), new Post(),new Post(), new Post(), new Post(), new Post(), new Post());
        PostService postServiceMock = spy(new PostService(postRepository, commentService, userService ));

        when(postRepository.getPostPageDescDate(10,0)).thenReturn(repositoryResponse);
        doReturn(new PostWithCommentsDto()).when(postServiceMock).getPostWithCommentsById(any(Post.class));
        List<PostWithCommentsDto> page = postServiceMock.getPostsWithCommentsByPageDesc(0, 10);
        assertEquals(10, page.size());
    }

    @Test
    @DisplayName("Get postdto by postId")
    public void postService_getPostById_returnPostDto(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Post post = new Post(1,1,"text","url", now);

        when(postRepository.getPostById(1)).thenReturn(post);
        when(commentService.getNumberOfCommentByPostId(1)).thenReturn(10);
        PostDto dto = postService.getPostById(1);

        assertEquals(10, dto.getAmountOfComments());
        assertEquals(1, dto.getPostId());
        assertEquals(1, dto.getAutorId());
        assertEquals("text", dto.getPostText());
        assertEquals("url", dto.getPostImgUrl());
        assertEquals(now, dto.getFechaPublicacion());
    }

}