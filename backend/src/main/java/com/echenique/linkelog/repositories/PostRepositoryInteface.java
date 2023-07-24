package com.echenique.linkelog.repositories;


import com.echenique.linkelog.models.Post;

import java.sql.Timestamp;
import java.util.List;

public interface PostRepositoryInteface {
    void addNewPost(int userId, String postText, String postImgUrl, Timestamp PostDate);
    Post getPostById(int id);
    List<Post> getPostsByUserId(int id);
    List<Post> getAllPosts();
    List<Post> getPostPageDescDate(int pageSize, int pageNumber);
    List<Post> getPostPageAscDate(int pageSize, int pageNumber);

    int deletePost(int id);
    int editPostImg(String newImg, int id);
    int editPostText(String newText, int id);
}
