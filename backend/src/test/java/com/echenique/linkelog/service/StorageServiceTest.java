package com.echenique.linkelog.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
@TestPropertySource(properties = "storage.profile-pictures-location=src/test/resources/profile-pictures")
@SpringBootTest
class StorageServiceTest {
    @Autowired
    StorageService storageService;
    @Value("${storage.profile-pictures-location}")
    String profilePicturesLocation;
    @Test
    @DisplayName("Stores picture to filesystem")
    public void storageService_storePicture_pictureIsStored() throws IOException {
        String imgToStorePath ="src/test/resources/pictures/cara-jpeg.jpg" ;
        BufferedImage img = ImageIO.read(new FileInputStream(imgToStorePath));

        String picId = storageService.storeProfilePictureAsJpg(img);
        Path storedPath = Paths.get(profilePicturesLocation).resolve(picId)
                .normalize().toAbsolutePath();
        assertTrue(ImageIO.read(new FileInputStream(storedPath.toString())) != null);
        Files.delete(storedPath);
    }
    @Test
    @DisplayName("Deletes picture from filesystem")
    public void storageService_deletePicture_pictureIsDeleted() throws IOException {
        String imgToStorePath ="src/test/resources/pictures/cara-jpeg.jpg" ;
        BufferedImage img = ImageIO.read(new FileInputStream(imgToStorePath));

        String picId= storageService.storeProfilePictureAsJpg(img);
        Path storedPath = Paths.get(profilePicturesLocation).resolve(picId)
                .normalize().toAbsolutePath();
        assertFalse(Files.notExists(storedPath));
        storageService.deleteProfilePicture(picId);
        assertTrue(Files.notExists(storedPath));

    }
    @Test
    @DisplayName("Deletes picture from filesystem")
    public void storageService_deletePicture_doesNothingIfPathIsNull() throws IOException {
        storageService.deleteProfilePicture(null);
    }
    @Test
    @DisplayName("returns file name")
    public void storageService_deletePicture_returnsFileName() throws IOException {
        String imgToStorePath ="src/test/resources/pictures/cara-jpeg.jpg" ;
        BufferedImage img = ImageIO.read(new FileInputStream(imgToStorePath));

        String picId= storageService.storeProfilePictureAsJpg(img);

        assertEquals(36, picId.length());
        storageService.deleteProfilePicture(picId);
    }

}