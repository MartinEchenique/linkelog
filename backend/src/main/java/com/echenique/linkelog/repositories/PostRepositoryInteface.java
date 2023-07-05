package com.echenique.linkelog.repositories;


import com.echenique.linkelog.models.Post;

import java.sql.Timestamp;
import java.util.List;

public interface PostRepositoryInteface {
    void addNewPost(Post post);
    void addNewPost(int userId, String postText, String postImgUrl, Timestamp PostDate);
    Post getPostById(int id);
    List<Post> getPostsByUserId(int id);
    List<Post> getAllPosts();

    List<Post> getPostPageDecendentOrder(int pageSize, int pageNumber);
    List<Post> getPostPageAscendentOrder(int pageSize, int pageNumber);
}
