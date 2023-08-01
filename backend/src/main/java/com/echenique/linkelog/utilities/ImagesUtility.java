package com.echenique.linkelog.utilities;

import com.echenique.linkelog.exceptions.IllegalFileTypeException;
import com.echenique.linkelog.exceptions.MultipartFileReadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImagesUtility {


    public static BufferedImage cropImage(BufferedImage img, int x, int y, int size){
        if (img.getWidth() <= img.getHeight() && img.getWidth() < (x + size)) size = img.getWidth() - x;
        else if(img.getHeight() < (y + size)) size = img.getHeight() - y;

        return img.getSubimage(x,y,size,size);
    }
    public static BufferedImage cropImage(BufferedImage img, int size){
        return cropImage(img, 0, 0, size);
    }
    public static BufferedImage getSquareImage(BufferedImage img){
        if(img.getWidth() != img.getHeight()){
        img = img.getHeight() > img.getWidth() ?
                cropImage(img, img.getWidth()) :
                cropImage(img, img.getHeight());
        }
        return img;
    }

    public static BufferedImage getOpaqueImageFromMultipartFile(MultipartFile file){
        try (InputStream inputStream = file.getInputStream()) {
            BufferedImage im = ImageIO.read(inputStream);

            if(im != null){
                BufferedImage newImage = new BufferedImage( im.getWidth(),im.getHeight(), BufferedImage.TYPE_INT_RGB);
                newImage.createGraphics().drawImage( im, 0, 0, Color.BLACK, null);
                return newImage;
            }else throw new IllegalFileTypeException("The file type is not supported");
        }catch (IOException e){
            throw new MultipartFileReadException("Error processing the file");
        }
    }
}
