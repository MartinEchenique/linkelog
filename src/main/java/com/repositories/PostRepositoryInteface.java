package com.repositories;

import com.models.Post;

import java.util.List;

public interface PostRepositoryInteface {
    void addNewPost(Post post);
    void addNewPost(int userId, String postText, String postImgUrl);
    Post getPostById(int id);
    List<Post> getPostsByUserId(int id);


}
