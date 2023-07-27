package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.userDto.AddUserDto;
import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.exceptions.FailedValidationException;
import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepo;

    @InjectMocks
    @Autowired
    UserService userService;
    @Test
    @DisplayName("Get user by id")
    public void userService_getUserDtoById_returnUserDto() {
        when(userRepo.getProfileById(1)).thenReturn(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass"));

        UserDto dto = userService.getUserDtoById(1);

        assertEquals(1,dto.getUserId());
        assertEquals("first", dto.getFirstName());
        assertEquals("last", dto.getLastName());
        assertEquals("company", dto.getCompanyName());
        assertEquals("pic", dto.getProfilePictureUrl());
        assertEquals("role", dto.getRole());
        assertEquals("user", dto.getUsername());

    }
    @Test
    @DisplayName("Get user by username")
    public void userService_getUserDtoByUsername_returnUserDto() {
        when(userRepo.getProfileByUsername("user")).thenReturn(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass"));

        UserDto dto = userService.getUserDtoByUsername("user");

        assertEquals(1,dto.getUserId());
        assertEquals("first", dto.getFirstName());
        assertEquals("last", dto.getLastName());
        assertEquals("company", dto.getCompanyName());
        assertEquals("pic", dto.getProfilePictureUrl());
        assertEquals("role", dto.getRole());
        assertEquals("user", dto.getUsername());

    }
    @Test
    @DisplayName("Add new user correct data")
    public void userService_addUser_addUserRepositoryIsCalled(){
        AddUserDto userToAdd = new AddUserDto("first", "last", null, null, null,"user", "passWORD123" );
        userService.addNewUser(userToAdd);
        verify(userRepo, times(1)).addNewProfile("first", "last", null, null, null, "user", "passWORD123");
    }
    @Test
    @DisplayName("Add new user firstname invalid")
    public void userService_addUser_throwsInvalidFirst(){
        AddUserDto userToAdd = new AddUserDto("fi", "last", null, null, null,"user", "passWORD123" );
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "first name less than 3 char");
    }
    @Test
    @DisplayName("Add new user lastname invalid")
    public void userService_addUser_throwsInvalidLast(){
        AddUserDto userToAdd = new AddUserDto("first", "la", null, null, null,"user", "passWORD123" );
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "last name less than 3 char");

    }
    @Test
    @DisplayName("Add new user username invalid")
    public void userService_addUser_throwsInvalidUsername(){
        AddUserDto userToAdd = new AddUserDto("first", "last", null, null, null,"us", "passWORD123" );

        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "user less than 3 char");

    }
    @Test
    @DisplayName("Add new user password invalid")
    public void userService_addUser_throwsInvalidPassword(){
        AddUserDto userToAdd = new AddUserDto("first", "last", null, null, null,"user", "pass1A" );

        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass less than 8 char");

        userToAdd.setPassword("pass12345");
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass no uppercase char");

        userToAdd.setPassword("PASS12345");
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass no lowercase char");

        userToAdd.setPassword("PASSwordunodos");
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass no number char");

    }
}