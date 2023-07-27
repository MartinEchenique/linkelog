package com.echenique.linkelog.controllers;

import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/user/{id}")
    public UserDto getUserById(@PathVariable int id){
        UserDto userToReturn = userService.getUserDtoById(id);
        return userToReturn;
    }
}
