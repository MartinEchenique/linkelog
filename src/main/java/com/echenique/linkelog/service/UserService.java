package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.UserDto;
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
        UserDto userDto = new UserDto(user.getUserId(),user.getFirstName(), user.getLastName(), user.getCompanyName(), user.getProfilePictureUrl(),user.getRole());
        return  userDto;
    }
}
