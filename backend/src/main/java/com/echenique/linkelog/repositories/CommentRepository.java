package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CommentRepository implements CommentRepositoryInterface {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int addComment(int postId, String commentText, int authorId, Timestamp pubDate) {
        String sql = "INSERT INTO comments (commenttext, pubdate, postid, autorid) VALUES(?, ?, ?, ?);";
        if (commentText == null ) commentText = "";
        return jdbcTemplate.update(sql,commentText, pubDate, postId, authorId );

    }

    @Override
    public Comment getCommentById(int id) {
        String sql = "SELECT * FROM comments c WHERE  c.commentid = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new CommentMapper(), id);

        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Comment> getCommentsByPostId(int id) {
        String sql = "SELECT * FROM comments c where c.postid = ?;";
        return jdbcTemplate.query(sql, new CommentMapper(), id) ;
    }


    public class CommentMapper implements RowMapper<Comment>{

        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setCommentId(rs.getInt("commentid"));
            comment.setAutor(rs.getInt("autorid"));
            comment.setTexto(rs.getString("commenttext"));
            comment.setFechaPublicacion(rs.getTimestamp("pubdate"));
            comment.setPostId(rs.getInt("postid"));
            return comment;
        }
    }
}
