package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comment;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/init.sql")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CommentRepositoryTest {
    @Autowired
    private CommentRepositoryInterface commentRepository;

    @Sql("/init_no_comments.sql")
    @Test
    @DisplayName("Save comment to db")
    public void commentRepository_addComment_returnOneModifiedRow(){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        int modifiedRows = commentRepository.addComment(1,"Comment text", 1, currentTime);
        Comment comment = commentRepository.getCommentById(1).get();

        assertEquals(1, modifiedRows, "Not returns modified field number expected -> (1)");
        assertEquals(1,comment.getCommentId(), "Comment id isn't  correct");
        assertEquals(1, comment.getPostId(), "Post id isn't  correct");
        assertEquals(1, comment.getAutor(), "User author isn't  Correct");
        assertEquals("Comment text", comment.getTexto(), "Comment text isn't  correct");
        assertEquals(currentTime.toString(), comment.getFechaPublicacion().toString(), "Pub date isn't correct");
    }

    @Test
    @DisplayName("get comment from db")
    public void commentRepository_getCommentById_returnsComment(){
        Comment comment = commentRepository.getCommentById(1).get();

        assertEquals(1,comment.getCommentId());
        assertEquals(1, comment.getAutor());
        assertEquals("2000-01-01 00:00:01.0", comment.getFechaPublicacion().toString());
        assertEquals("Comment 1",comment.getTexto());
    }

    @Test
    @DisplayName("get comment from db id doesn't exist")
    public void commentRepository_getCommentById_returnsNullIfIdDoesntExist(){
        boolean isOptionalEmpty = commentRepository.getCommentById(-1).isEmpty();

        assertTrue(isOptionalEmpty);

    }

    @Test
    @DisplayName("throws if data is missing")
    public void commentRepository_addComment_throwsIfRequiredDataMissing(){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        assertThrows(DataIntegrityViolationException.class, () -> commentRepository.addComment(0,"Comment text", 1, currentTime));
        assertThrows(DataIntegrityViolationException.class, () -> commentRepository.addComment(1,"Comment text", 0, currentTime));
        assertThrows(DataIntegrityViolationException.class, () -> commentRepository.addComment(1,"Comment text", 1, null));
    }

    @Test
    @DisplayName("throws if Fk doesn't exist")
    public void commentRepository_addComment_throwsIfFkDoesntExist(){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        assertThrows(DataIntegrityViolationException.class, () -> commentRepository.addComment(0,"Comment text", 1, currentTime));
        assertThrows(DataIntegrityViolationException.class, () -> commentRepository.addComment(1,"Comment text", 0, currentTime));
        assertThrows(DataIntegrityViolationException.class, () -> commentRepository.addComment(1,"Comment text", 1, null));
    }
    @Sql("/init_no_comments.sql")

    @Test
    @DisplayName("If comment text is missing save void String")
    public void commentRepository_addComment_saveEmptyString(){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        int modifiedRows = commentRepository.addComment(1,null, 1, currentTime);
        assertEquals(1, modifiedRows, "Not returns modified field number (1)");
        Comment comment = commentRepository.getCommentById(1).get();
        assertEquals("", comment.getTexto(), "Comment text isn't  empty");
    }

    @Test
    @DisplayName("Gets comments by post id")
    public void commentRepository_getCommentByPostId_returnCommentList(){
        List<Comment> comments =  commentRepository.getCommentsByPostId(3);
        assertEquals(3,comments.size(), "The size of list is incorrect");
    }

    @Test
    @DisplayName("Counts comments by post")
    public void commentRepository_countComment_getCountNumber(){
        assertEquals(20, commentRepository.countComments(4));
    }

    @Test
    @DisplayName("Get comment by user id")
    public void commentRepository_getCommentByUserId_returnComments(){
        List<Comment> comments = commentRepository.getCommentByUserId(3);
        assertEquals(3, comments.size());
    }

    @Test
    @DisplayName("Delete comment")
    public void commentRepository_deleteCommentById_returnOneModifiedRows(){
        int modifiedRows = commentRepository.deleteComment(1);
        assertEquals(1, modifiedRows);
        assertTrue(commentRepository.getCommentById(1).isEmpty());
    }
    @Test
    @DisplayName("Delete comment doesn't delete")
    public void commentRepository_deleteCommentById_returnZeroModifiedRows(){
        int modifiedRows = commentRepository.deleteComment(100);
        assertEquals(0, modifiedRows);
        assertTrue(commentRepository.getCommentById(100).isEmpty());
    }

    @Test
    @DisplayName("Edit comment")
    public void commentRepository_editComment_modifiesCommentText(){
        commentRepository.editComment("Succes", 1);
        String newText = commentRepository.getCommentById(1).get().getTexto();
        assertEquals("Succes", newText);
    }

}