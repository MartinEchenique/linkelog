package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.userDto.AddUserDto;
import com.echenique.linkelog.dto.userDto.EditProfilePictureDto;
import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.exceptions.FailedValidationException;
import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@TestPropertySource(properties = "storage.profile-pictures-location=src/test/resources/profile-pictures")
@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepo;
    @MockBean
    private StorageService storageService;

    @Value("${storage.profile-pictures-location}")
    private String storageLocation;
    @InjectMocks
    @Autowired
    UserService userService;
    @Test
    @DisplayName("Get user by id")
    public void userService_getUserDtoById_returnUserDto() {
        when(userRepo.getProfileById(1)).thenReturn(Optional.of(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass")));

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
        when(userRepo.getProfileByUsername("user")).thenReturn(Optional.of(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass")));

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

        AddUserDto userToAdd = new AddUserDto("first", "last", null, null,"user", "passWORD123" );
        userService.addNewUser(userToAdd);
        verify(userRepo, times(1)).addNewProfile("first", "last", null,null,"default", "user", "passWORD123");
    }
    @Test
    @DisplayName("Add new user firstname invalid")
    public void userService_addUser_throwsInvalidFirst(){
        AddUserDto userToAdd = new AddUserDto("fi", "last", null,  null,"user", "passWORD123" );
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "first name less than 3 char");
    }
    @Test
    @DisplayName("Add new user lastname invalid")
    public void userService_addUser_throwsInvalidLast(){
        AddUserDto userToAdd = new AddUserDto("first", "la", null,  null,"user", "passWORD123" );
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "last name less than 3 char");

    }
    @Test
    @DisplayName("Add new user username invalid")
    public void userService_addUser_throwsInvalidUsername(){
        AddUserDto userToAdd = new AddUserDto("first", "last", null, null,"us", "passWORD123" );

        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "user less than 3 char");

    }
    @Test
    @DisplayName("Add new user password invalid")
    public void userService_addUser_throwsInvalidPassword(){
        AddUserDto userToAdd = new AddUserDto("first", "last",  null, null,"user", "pass1A" );

        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass less than 8 char");

        userToAdd.setPassword("pass12345");
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass no uppercase char");

        userToAdd.setPassword("PASS12345");
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass no lowercase char");

        userToAdd.setPassword("PASSwordunodos");
        assertThrows(FailedValidationException.class, ()-> userService.addNewUser(userToAdd), "pass no number char");

    }
    @Test
    @DisplayName("Edit user profile picture -> returns id")
    public void userService_editUserProfilePicture_returnsPictureId() throws IOException {
      MockMultipartFile mockMultipart = new MockMultipartFile("file",new FileInputStream("src/test/resources/pictures/cara-jpeg.jpg"));
      when(storageService.storeProfilePictureAsJpg(any(BufferedImage.class))).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        when(userRepo.getProfileById(1)).thenReturn(Optional.of(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass")));

      String pictureId =  userService.editUserProfilePicture(mockMultipart, new EditProfilePictureDto(),1);

      assertEquals(36, pictureId.length());
    }
    @Test
    @DisplayName("Edit user profile picture -> calls repo")
    public void userService_editUserProfilePicture_storesOnDb() throws IOException {
        MockMultipartFile mockMultipart = new MockMultipartFile("file",new FileInputStream("src/test/resources/pictures/cara-jpeg.jpg"));
        when(userRepo.getProfileById(1)).thenReturn(Optional.of(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass")));

        String pictureId =  userService.editUserProfilePicture(mockMultipart, new EditProfilePictureDto(),  1);
        verify(userRepo,times(1)).editUserPicture(pictureId, 1);
    }

    @Test
    @DisplayName("Edit user profile picture -> stores picture on fileSystem")
    public void userService_editUserProfilePicture_storesOnFileSystem() throws IOException {
        String mockImgPath ="src/test/resources/pictures/cara-jpeg.jpg" ;
        Path path = Paths.get(storageLocation);
        when(userRepo.getProfileById(1)).thenReturn(Optional.of(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass")));

        userService.editUserProfilePicture(new MockMultipartFile("file", new FileInputStream(mockImgPath)), new EditProfilePictureDto(),1);
        verify(storageService, times(1)).storeProfilePictureAsJpg(any(BufferedImage.class));
    }
    @Test
    @DisplayName("Edit user profile picture -> stores picture on fileSystem")
    public void userService_editUserProfilePicture_removesOldFromFileSystem() throws IOException {
        String mockImgPath ="src/test/resources/pictures/cara-jpeg.jpg" ;
        when(userRepo.getProfileById(1)).thenReturn(Optional.of(new UserProfile(1, "first", "last",
                "company", "pic", "role","user", "pass")));

        Path path = Paths.get(storageLocation);
        userService.editUserProfilePicture(new MockMultipartFile("file", new FileInputStream(mockImgPath)), new EditProfilePictureDto(),1);
        verify(storageService, times(1)).storeProfilePictureAsJpg(any(BufferedImage.class));
        verify(storageService, times(1)).storeProfilePictureAsJpg(any(BufferedImage.class));

    }
}