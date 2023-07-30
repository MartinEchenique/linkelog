package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;


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
                              String userName,
                              String password){
        String sql = "INSERT INTO profile (firstname, lastname, companyname,  userrole, username, password) " +
                " values(?, ?, ?, ?,?,?) ";
        jdbcTemplate.update(sql, firstName, lastName ,companyName, role, userName, password);
    }

    @Override
    public UserProfile getProfileById(int id){
        String sql = "SELECT * from profile p WHERE p.userid = ? " ;
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);

    }

    @Override
    public UserProfile getProfileByUsername(String username) {
        String sql = "SELECT * from profile p WHERE p.username = ? " ;
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
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
