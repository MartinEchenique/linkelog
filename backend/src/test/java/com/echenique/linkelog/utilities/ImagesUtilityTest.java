package com.echenique.linkelog.utilities;

import com.echenique.linkelog.exceptions.CoordinatesOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    @DisplayName("image coordinate is bigger than image size ")
    public void imagesUtility_cropImage_throwsException() throws IOException {
        BufferedImage img = ImageIO.read(new FileInputStream("src/test/resources/pictures/250x200.jpg"));
        assertThrows(CoordinatesOutOfRangeException.class, () -> ImagesUtility.cropImage(img,260,0,250));
        assertThrows(CoordinatesOutOfRangeException.class, () -> ImagesUtility.cropImage(img,0,250,250));

    }

    @Test
    @DisplayName("image is resized")
    public void imagesUtility_resizeImage_sizeChanges() throws IOException {
        String imgToStorePath ="src/test/resources/pictures/delete.jpg" ;
        BufferedImage img = ImageIO.read(new FileInputStream("src/test/resources/pictures/cara-jpeg.jpg"));
        img = ImagesUtility.ResizeImg(img,1000,300);
        ImageIO.write(img, "jpg", new FileOutputStream(imgToStorePath));
        assertEquals(300, img.getHeight());
        assertEquals(1000, img.getWidth());

    }
}

