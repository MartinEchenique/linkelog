package com.echenique.linkelog.utilities;

import com.echenique.linkelog.exceptions.CoordinatesOutOfRangeException;
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

        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        //validates crop options
        if(imgWidth < x || imgHeight < y) throw new CoordinatesOutOfRangeException("start point coordinates are out of picture");
        if ( imgWidth <= imgHeight && imgWidth < (x + size)) size = imgWidth - x;
        else if(imgHeight < (y + size)) size = imgHeight - y;

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

    public static BufferedImage ResizeImg(BufferedImage img, int width, int height) {
        BufferedImage newImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        newImage.createGraphics().drawImage( img, 0, 0,width,height, Color.BLACK, null);
        return newImage;
    }
}
