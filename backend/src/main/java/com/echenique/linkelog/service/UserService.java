package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.userDto.AddUserDto;
import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.UserRepository;
import com.echenique.linkelog.utilities.ImagesUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    StorageService storageService;
    private final int DEFAULT_PROFILE_PICTURE_SIZE = 200;
    private final int DEFAULT_PROFILE_PICTURE_X = 0;
    private final int DEFAULT_PROFILE_PICTURE_Y = 0;

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
                userToAdd.getRole(),
                userToAdd.getUsername(),
                userToAdd.getPassword());
    }

    public String editUserProfilePicture(MultipartFile picture, Integer userId) throws IOException {
        return editUserProfilePicture(picture, DEFAULT_PROFILE_PICTURE_X, DEFAULT_PROFILE_PICTURE_Y, DEFAULT_PROFILE_PICTURE_SIZE, userId);
    }
    public String editUserProfilePicture(MultipartFile picture, Integer x, Integer y, Integer size, Integer userId) throws IOException {

        BufferedImage img = ImagesUtility.getOpaqueImageFromMultipartFile(picture);
        img = ImagesUtility.cropImage(img, x, y, size);
        String pictureId = storageService.storeProfilePictureAsJpg(img);

        userRepo.editUserPicture(pictureId, userId);

        return pictureId;
    }
}
