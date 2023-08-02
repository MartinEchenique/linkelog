package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.userDto.AddUserDto;
import com.echenique.linkelog.dto.userDto.EditProfilePictureDto;
import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.exceptions.MultipartFileReadException;
import com.echenique.linkelog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserDtoById(id);
    }

    @PreAuthorize("T(com.echenique.linkelog.security.LoggedUserHelper).isLoggedId(#id)")
    @PostMapping(value = "/picture/{id}")
    public void editUserProfilePicture(@ModelAttribute EditProfilePictureDto options, @RequestParam MultipartFile picture, @PathVariable int id) {
        try {
            userService.editUserProfilePicture(picture, options, id);
        } catch (IOException e) {
            throw new MultipartFileReadException("Error processing the file");
        }
    }
    @PostMapping(value = "/new")
    public void addUser(@ModelAttribute AddUserDto user) {
        userService.addNewUser(user);
    }
}