package com.echenique.linkelog.repositories;


import com.echenique.linkelog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class PostRepository implements PostRepositoryInteface {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addNewPost(Post post) {

    }

    @Override
    public void addNewPost(int userId, String postText, String postImgUrl) {
        String sql = "INSERT INTO post (userprofileid, posttext, postimgurl) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, postText, postImgUrl);
    }

    @Override
    public Post getPostById(int id) {
        String sql = "SELECT * FROM post p where p.postid = ?";
        return jdbcTemplate.queryForObject(sql,new PostMapper(), id );
    }

    @Override
    public List<Post> getPostsByUserId(int id) {
        String sql = "SELECT * FROM post p where p.userprofileid = ?";
        return jdbcTemplate.query(sql,new PostMapper(), id );
    }
    public List<Post> getAllPosts() {
        String sql = "SELECT * FROM post";
        return jdbcTemplate.query(sql,new PostMapper());
    }

    private class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getInt("postid"));
            post.setPostText(rs.getString("postText"));
            post.setAutorId(rs.getInt("userprofileid"));
            post.setPostImgUrl(rs.getString("postimgurl"));
            post.setFechaPublicacion(rs.getDate("pubdate"));

            return post;
        }
    }
}
