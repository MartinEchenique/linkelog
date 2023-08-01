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


    public void deleteProfilePicture(Path path) throws IOException {
        if(null == path) return;
        Files.deleteIfExists(path);
    }
}
