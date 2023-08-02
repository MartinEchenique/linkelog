package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.Comment;
import com.echenique.linkelog.models.UserProfile;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


@Repository
@Transactional
public class UserRepository implements UserRepositoryInterface {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNewProfile(String firstName,
                              String lastName,
                              String companyName,
                              String role,
                              String profilepicture,
                              String userName,
                              String password){
        String sql = "INSERT INTO profile (firstname, lastname, companyname,  userrole, profilepictureurl, username, password) " +
                " values(?, ?, ?, ?, ?, ?, ?) ";
        jdbcTemplate.update(sql, firstName, lastName ,companyName, role, profilepicture, userName, password);
    }

    @Override
    public Optional<UserProfile> getProfileById(int id){
        String sql = "SELECT * from profile p WHERE p.userid = ? " ;
        UserProfile result;
        try {
            result = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);

        }catch (EmptyResultDataAccessException e){
            result = null;
        }
        Optional<UserProfile> optionalUser = Optional.ofNullable(result);
        return optionalUser;
    }

    @Override
    public Optional<UserProfile> getProfileByUsername(String username) {
        String sql = "SELECT * from profile p WHERE p.username = ? " ;
        UserProfile result;
        try {
            result = jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);

        }catch (EmptyResultDataAccessException e){
            result = null;
        }
        Optional<UserProfile> optionalUser = Optional.ofNullable(result);
        return optionalUser;
    }

    @Override
    public int editUserRole(String newRole, int id) {
        String sql = "UPDATE profile SET userrole=? where userid=?";
        return jdbcTemplate.update(sql, newRole,id);
    }

    @Override
    public int editUserCompany(String newCompany, int id) {
        String sql = "UPDATE profile SET companyname=? where userid=?";
        return jdbcTemplate.update(sql, newCompany,id);
    }

    @Override
    public int editUserCompanyRole(String newRole, String newCompany, int id) {
        int modificationsOnRows = editUserCompany(newCompany, id);
        modificationsOnRows +=  editUserRole(newRole, id);
        return ( modificationsOnRows == 0) ? 0 : 1;
    }

    @Override
    public int editUserPicture(String newPicture, int id) {
        String sql = "UPDATE profile SET profilepictureurl=? where userid=?";
        return jdbcTemplate.update(sql, newPicture,id);
    }

    @Override
    public int editUserPassword(String newPass, int id) {
        String sql = "UPDATE profile SET password=? where userid=?";
        return jdbcTemplate.update(sql, newPass,id);
    }

    private class UserRowMapper implements RowMapper<UserProfile> {

        @Override
        public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("userid");
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String companyName = rs.getString("companyname");
            String profilePictureUrl = rs.getString("profilepictureurl");
            String role = rs.getString("userrole");
            String username = rs.getString("username");
            String pass = rs.getString("password");
          return  new UserProfile(id ,firstName, lastName ,companyName,profilePictureUrl, role, username, pass);
        }
    }
    
}
