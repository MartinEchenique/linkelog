package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.exceptions.MultipartFileReadException;
import com.echenique.linkelog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "{id}")
    public UserDto getUserById(@PathVariable int id){
        return userService.getUserDtoById(id);
    }
    @PostMapping(value = "/picture/{id}")
    public void editUserProfilePicture(@RequestParam MultipartFile picture,
                                       @PathVariable int id,
                                       @RequestParam(required = false) Integer x,
                                       @RequestParam(required = false) Integer y,
                                       @RequestParam(required = false) Integer size){
        try {
            if(x == null || y == null || size == null) userService.editUserProfilePicture(picture, id);
            else userService.editUserProfilePicture(picture, x, y, size,id);

        }catch (IOException e){
            throw new MultipartFileReadException("Error processing the file");
        }
    }
}
