package com.echenique.linkelog.utilities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ImagesUtilityTest {

    @Test
    @DisplayName("image is cropped from x=0 y=0 ")
    public void imagesUtility_cropImage_imageIsTheGivenWidth() throws IOException {
        BufferedImage img = ImageIO.read(new FileInputStream("src/test/resources/pictures/cara-jpeg.jpg"));
        img = ImagesUtility.cropImage(img,200);
        assertEquals(200, img.getHeight());
        assertEquals(200, img.getWidth());
    }
    @Test
    @DisplayName("image crop size is bigger than image size ")
    public void imagesUtility_cropImage_cropsToMinSize() throws IOException {
        BufferedImage img = ImageIO.read(new FileInputStream("src/test/resources/pictures/250x200.jpg"));
        img = ImagesUtility.cropImage(img,0,0,250);
        assertEquals(200, img.getHeight());
        assertEquals(200, img.getWidth());
    }
    @Test
    @DisplayName("image crop size is bigger than image size moved from origen ")
    public void imagesUtility_cropImage_temporal() throws IOException {
        BufferedImage img = ImageIO.read(new FileInputStream("src/test/resources/pictures/200x200divided.jpg"));
        img = ImagesUtility.cropImage(img,100,100,250);
        assertEquals(100, img.getHeight());
        assertEquals(100, img.getWidth());

    }
}