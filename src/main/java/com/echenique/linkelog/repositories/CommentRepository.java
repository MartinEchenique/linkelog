package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CommentRepository implements CommentRepositoryInterface {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addComment(int postId, String commentText, int authorId, Date pubDate) {
        String sql = "INSERT INTO comments (commenttext, pubdate, postid, autorid) VALUES(?, ?, ?, ?);";
        jdbcTemplate.update(sql,commentText, pubDate, postId, authorId );
    }

    @Override
    public Comentario getCommentById(int id) {
        String sql = "SELECT * FROM comments c WHERE  c.commentid = ?;";
        return jdbcTemplate.queryForObject(sql, new CommentMapper(), id);
    }

    @Override
    public List<Comentario> getCommentsByPostId(int id) {
        String sql = "SELECT * FROM comments c where c.postid = ?;";
        return jdbcTemplate.query(sql, new CommentMapper(), id) ;
    }


    public class CommentMapper implements RowMapper<Comentario>{

        @Override
        public Comentario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comentario comment = new Comentario();
            comment.setCommentId(rs.getInt("commentid"));
            comment.setAutor(rs.getInt("autorid"));
            comment.setTexto(rs.getString("commenttext"));
            comment.setFechaPublicacion(rs.getDate("pubdate"));
            comment.setPostId(rs.getInt("postid"));
            return comment;
        }
    }
}
