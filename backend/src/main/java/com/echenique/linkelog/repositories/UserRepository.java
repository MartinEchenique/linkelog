package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.UserProfile;
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
public class UserRepository implements UserRepositoryInterface {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNewProfile(UserProfile user){
        String sql = "INSERT INTO profile (firstname, lastname, companyname, profilepictureurl, userrole) " +
                " values(?, ?, ?, ?, ?) ";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName() , user.getCompanyName(), user.getProfilePictureUrl(), user.getRole());
    }

    @Override
    public void addNewProfile(String firstName, String lastName, String companyName, String profilePictureUrl, String role){
        String sql = "INSERT INTO profile (firstname, lastname, companyname, profilepictureurl, userrole) " +
                " values(?, ?, ?, ?, ?) ";
        jdbcTemplate.update(sql, firstName, lastName ,companyName,profilePictureUrl, role);
    }

    @Override
    public UserProfile getProfileById(int id){
        String sql = "SELECT * from profile p WHERE p.userid = ? " ;
        List<UserProfile> users= jdbcTemplate.query(sql, new UserRowMapper(), id);
        return users.get(0);
    }
    private class UserRowMapper implements RowMapper<UserProfile> {

        @Override
        public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String companyName = rs.getString(4);
            String profilePictureUrl = rs.getString(5);
            String role = rs.getString(6);
          return  new UserProfile(id ,firstName, lastName ,companyName,profilePictureUrl, role);
        }
    }
    
}
