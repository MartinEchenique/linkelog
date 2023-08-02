package com.echenique.linkelog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {
    @Value("${storage.profile-pictures-location}")
    private String profilePictureLocation;
    @Value("${user.profile-pictures-default}")
    private String DEFAULT_PICTURE_ID;
    public String storeProfilePictureAsJpg(BufferedImage pic) throws IOException {
        String pictureId = UUID.randomUUID().toString();
        Path storagePath = Paths.get(profilePictureLocation).resolve(
                        Paths.get(pictureId))
                .normalize().toAbsolutePath();

        File destinationFile = new File(storagePath.toUri());
        destinationFile.createNewFile();
        ImageIO.write(pic, "jpg", destinationFile);
        return pictureId;
    }


    public void deleteProfilePicture(String pictureId) throws IOException {
        if(null == pictureId || pictureId.isBlank() || pictureId.equals(DEFAULT_PICTURE_ID) ) return;
        Path path = Paths.get(profilePictureLocation).resolve(
                        Paths.get(pictureId))
                .normalize().toAbsolutePath();
        Files.deleteIfExists(path);
    }
}
