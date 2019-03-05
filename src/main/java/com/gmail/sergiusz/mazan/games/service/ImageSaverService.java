package com.gmail.sergiusz.mazan.games.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@PropertySource("classpath:/upload.properties")
public class ImageSaverService {

    private Environment environment;

    @Autowired
    private void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    public void saveImage(MultipartFile cover, int id) {
        File imageFile = new File(environment.getProperty("upload.location") +
                environment.getProperty("upload.prefix") + id);
        try {
            BufferedImage image = ImageIO.read(cover.getInputStream());
            ImageIO.write(image, "png", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
