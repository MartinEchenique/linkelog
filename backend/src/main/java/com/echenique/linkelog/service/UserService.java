package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.userDto.AddUserDto;
import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public UserDto getUserDtoById(int userId){
        UserProfile user = userRepo.getProfileById(userId);
        UserDto userDto = new UserDto(user.getUserId(),user.getFirstName(), user.getLastName(), user.getCompanyName(), user.getProfilePictureUrl(),user.getRole(), user.getUsername());
        return  userDto;
    }

    public UserDto getUserDtoByUsername(String username) {
        UserProfile user = userRepo.getProfileByUsername(username);
        UserDto userDto = new UserDto(user.getUserId(),user.getFirstName(), user.getLastName(), user.getCompanyName(), user.getProfilePictureUrl(),user.getRole(), user.getUsername());
        return  userDto;
    }
    public void addNewUser(AddUserDto userToAdd){
        userToAdd.validate();
        userRepo.addNewProfile(userToAdd.getFirstName(),
                userToAdd.getLastName(),
                userToAdd.getCompanyName(),
                userToAdd.getProfilePictureUrl(),
                userToAdd.getRole(),
                userToAdd.getUsername(),
                userToAdd.getPassword());
    }
}
