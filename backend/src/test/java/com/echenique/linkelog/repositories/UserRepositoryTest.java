package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.UserProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql("/init.sql")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;
    @Test
    @DisplayName("Get profile by id")
    public void userRepository_getProfileById_returnsUser(){
        UserProfile profile = userRepo.getProfileById(1);
        assertEquals(1, profile.getUserId());
        assertEquals("user 1", profile.getUsername());
    }

    @Test
    @DisplayName("Get profile by username")
    public void userRepository_getProfileByUsername_returnsUser(){
        UserProfile profile = userRepo.getProfileByUsername("user 1");
        assertEquals(1, profile.getUserId());
        assertEquals("user 1", profile.getUsername());
    }
    @Test
    @DisplayName("Edit user role")
    public void userRepository_editUserRole_justRoleIsEdited(){
        UserProfile initialUser = userRepo.getProfileById(1);
        int editedRows = userRepo.editUserRole("new role", 1);
        UserProfile editedUser = userRepo.getProfileById(1);
        assertEquals(1,editedRows );
        assertEquals("new role",editedUser.getRole());
        assertEquals(initialUser.getCompanyName(), editedUser.getCompanyName());
    }
    @Test
    @DisplayName("Edit user company")
    public void userRepository_editUserCompany_justCompanyIsEdited(){
        UserProfile initialUser = userRepo.getProfileById(1);
        int editedRows = userRepo.editUserCompany("new company", 1);
        UserProfile editedUser = userRepo.getProfileById(1);
        assertEquals(1,editedRows );
        assertEquals(initialUser.getRole(),editedUser.getRole());
        assertEquals("new company", editedUser.getCompanyName());
    }
    @Test
    @DisplayName("Edit company and role")
    public void userRepository_editUserCompanyAndRole_companyAndRoleEdited(){
        int modifiedRows = userRepo.editUserCompanyRole("new role", "new company", 1);
        UserProfile modifiedUser = userRepo.getProfileById(1);
        assertEquals(1, modifiedRows);
        assertEquals("new role", modifiedUser.getRole());
        assertEquals("new company", modifiedUser.getCompanyName());
    }
    @Test
    @DisplayName("Edit user picture")
    public void userRepository_editUserPicture_pictureIsEdited(){
        UserProfile initialUser = userRepo.getProfileById(1);
        int editedRows = userRepo.editUserPicture("new picture", 1);
        UserProfile editedUser = userRepo.getProfileById(1);
        assertEquals(1,editedRows );
        assertEquals(initialUser.getRole(),editedUser.getRole());
        assertEquals(initialUser.getCompanyName(),editedUser.getCompanyName());
        assertEquals("new picture", editedUser.getProfilePictureUrl());
    }

    @Test
    @DisplayName("Edit user password")
    public void userRepository_editUserPassword_passwordIsEdited(){
        UserProfile initialUser = userRepo.getProfileById(1);
        int editedRows = userRepo.editUserPassword("newpass", 1);
        UserProfile editedUser = userRepo.getProfileById(1);
        assertEquals(1,editedRows );
        assertEquals("newpass", editedUser.getPassword());
    }

    @Test
    @Sql("/init_no_user.sql")
    @DisplayName("register new user")
    public void userRepository_registerNewUser_userIsRegistered(){
        userRepo.addNewProfile("first", "last", "company", "url", "role", "name", "pass");

        UserProfile profile = userRepo.getProfileById(1);

        assertEquals("company",profile.getCompanyName());
        assertEquals("role", profile.getRole());
        assertEquals("first", profile.getFirstName());
        assertEquals("last", profile.getLastName());
        assertEquals("url", profile.getProfilePictureUrl());
        assertEquals("name", profile.getUsername());
        assertEquals("pass", profile.getPassword());
    }

}