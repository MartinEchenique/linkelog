package com.echenique.linkelog.controllers;

import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepositoryInterface userRepo;
    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/user/{id}")
    public UserProfile getUserById(@PathVariable int id){
        UserProfile userToReturn = userRepo.getProfileById(id);
        return userToReturn;
    }
}
