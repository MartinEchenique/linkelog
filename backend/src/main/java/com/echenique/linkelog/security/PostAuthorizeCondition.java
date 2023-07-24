package com.echenique.linkelog.security;

import com.echenique.linkelog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostAuthorizeCondition {
    @Autowired
    private PostRepository postRepo;
    public boolean loggedOwnsPost(int postId){
        int postAuthorId = postRepo.getPostById(postId).getAutorId();
        int loggedUserId = LoggedUserHelper.getLoggedId();
        return postAuthorId == loggedUserId;
    }
}
