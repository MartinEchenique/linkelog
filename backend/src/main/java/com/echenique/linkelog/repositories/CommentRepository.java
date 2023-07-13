package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public  Optional<Comment> getCommentById(int id) {
        String sql = "SELECT * FROM comments c WHERE  c.commentid = ?;";
        Comment result;
        try {
            result = jdbcTemplate.queryForObject(sql, new CommentMapper(), id);

        }catch (EmptyResultDataAccessException e){
            result = null;
        }
        Optional<Comment> optionalComment = Optional.ofNullable(result);
        return optionalComment;
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        String sql = "SELECT * FROM comments c where c.postid = ?;";
        return jdbcTemplate.query(sql, new CommentMapper(), postId) ;
    }

    @Override
    public int countComments(int postId) {
        String sql = "SELECT COUNT(C.commentid) AS count FROM comments c where c.postid = ?;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, postId);
        sqlRowSet.first();
        return sqlRowSet.getInt("count");

    }

    @Override
    public List<Comment> getCommentByUserId(int userId) {
        String sql = "SELECT * from comments c where c.autorid = ?;";
        return jdbcTemplate.query(sql, new CommentMapper(), userId);
    }

    @Override
    public int deleteComment(int commentId) {
        String sql = "DELETE from comments c where c.commentid = ?;";
        return jdbcTemplate.update(sql, commentId);
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
