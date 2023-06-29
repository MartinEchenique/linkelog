package com.main;

import com.config.ProjectConfig;
import com.repositories.CommentRepositoryInterface;
import com.repositories.PostRepositoryInteface;
import com.repositories.UserRepositoryInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)){
             UserRepositoryInterface userRepository = context.getBean(UserRepositoryInterface.class);
            // userRepository.getProfileById(3L);
           PostRepositoryInteface postRepo = context.getBean(PostRepositoryInteface.class);
            //postRepo.addNewPost(4L, "Hello World! jas jas ajs ", "");
            //System.out.println(postRepo.getPostById(11L).getPostText());
            CommentRepositoryInterface commentRepo = context.getBean(CommentRepositoryInterface.class);
            //commentRepo.addComment(1, "yp soy seguuuu!!", 1, new Date(System.currentTimeMillis()));
            System.out.println( commentRepo.getCommentsByPostId(1));
            //System.out.println(postRepo.getPostsByUserId(2));
        }

    }
}