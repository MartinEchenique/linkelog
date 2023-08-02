package com.echenique.linkelog.security;

import com.echenique.linkelog.service.CommentService;
import com.echenique.linkelog.service.PostService;
import org.springframework.stereotype.Component;

@Component
public class CommentAuthorizeCondition {

  private final CommentService commentService;
  private final PostService postService;
  public CommentAuthorizeCondition(CommentService commentService,
                                   PostService postService){
      this.commentService = commentService;
      this.postService = postService;
  }
    public boolean loggedUserOwnsComment(int commentId){
      int userId = LoggedUserHelper.getLoggedId();
      int commentOwnerId = commentService.getCommentDtoById(commentId).getAutor().getUserId();
      return userId == commentOwnerId;
  }
    public boolean loggedUserOwnsPost(int commentId){
        int userId = LoggedUserHelper.getLoggedId();
        int postId = commentService.getCommentDtoById(commentId).getPostId();
        int postOwnerId = postService.getPostById(postId).getAutorId();
        return userId == postOwnerId;
    }


}
