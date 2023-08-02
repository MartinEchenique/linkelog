package com.echenique.linkelog.service;

import com.echenique.linkelog.dto.userDto.AddUserDto;
import com.echenique.linkelog.dto.userDto.EditProfilePictureDto;
import com.echenique.linkelog.dto.userDto.UserDto;
import com.echenique.linkelog.exceptions.FailedValidationException;
import com.echenique.linkelog.exceptions.ResourceNotFound;
import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.UserRepository;
import com.echenique.linkelog.utilities.ImagesUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    StorageService storageService;
    @Value("${user.profile-pictures-default}")
    private String DEFAULT_PROFILE_PICTURE;
    private final int DEFAULT_PROFILE_PICTURE_SIZE = 200;
    private final int DEFAULT_PROFILE_PICTURE_X = 0;
    private final int DEFAULT_PROFILE_PICTURE_Y = 0;

    public UserDto getUserDtoById(int userId){
        UserProfile user = userRepo.getProfileById(userId).orElseThrow(()-> new ResourceNotFound("User not found"));
        UserDto userDto = new UserDto(user.getUserId(),user.getFirstName(), user.getLastName(), user.getCompanyName(), user.getProfilePictureUrl(),user.getRole(), user.getUsername());
        return  userDto;
    }

    public UserDto getUserDtoByUsername(String username) {
        UserProfile user = userRepo.getProfileByUsername(username).orElseThrow(()-> new ResourceNotFound("User not found"));
        UserDto userDto = new UserDto(user.getUserId(),user.getFirstName(), user.getLastName(), user.getCompanyName(), user.getProfilePictureUrl(),user.getRole(), user.getUsername());
        return  userDto;
    }
    public void addNewUser(AddUserDto userToAdd){
        userToAdd.validate();
        if(userRepo.getProfileByUsername(userToAdd.getUsername()).isPresent()) throw new FailedValidationException("Username is in use");
        userRepo.addNewProfile(userToAdd.getFirstName(),
                userToAdd.getLastName(),
                userToAdd.getCompanyName(),
                userToAdd.getRole(),
                DEFAULT_PROFILE_PICTURE,
                userToAdd.getUsername(),
                userToAdd.getPassword());
    }

    public String editUserProfilePicture(MultipartFile picture, EditProfilePictureDto options, int userId) throws IOException {
        String oldPictureId = userRepo.getProfileById(userId).orElseThrow(()-> new ResourceNotFound("User not found")).getProfilePictureUrl();

        BufferedImage img = ImagesUtility.getOpaqueImageFromMultipartFile(picture);

        img = (options.getSize() == null || options.getX() == null || options.getY() == null ) ?
                ImagesUtility.getSquareImage(img) :
                ImagesUtility.cropImage(img, options.getX(), options.getY(), options.getSize());

        img = ImagesUtility.ResizeImg(img, DEFAULT_PROFILE_PICTURE_SIZE, DEFAULT_PROFILE_PICTURE_SIZE);
        String pictureId = storageService.storeProfilePictureAsJpg(img);
        userRepo.editUserPicture(pictureId, userId);
        storageService.deleteProfilePicture(oldPictureId);
        return pictureId;
    }

}
