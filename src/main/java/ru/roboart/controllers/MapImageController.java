package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.MapImage;
import ru.roboart.repositories.MapImageRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

/**
 * Created by Kida on 06.01.2017.
 */

@RestController
@RequestMapping("/map")
public class MapImageController extends MainRestController<MapImage> {

   @Autowired
    public MapImageController(MapImageRepository mapRepository) {
        repository = mapRepository;
    }


    @RequestMapping("/picture/{id}")
    @ResponseBody
    public HttpEntity<byte[]> getArticleImage(@PathVariable String id) throws IOException {

//        logger.info("Requested picture >> " + id + " <<");

        // 1. download img from http://internal-picture-db/id.jpg ...
        BufferedImage bufferedImage = ImageIO.read(new File(String.format("C:\\img\\%s.jpg", id)));
        byte[] data = new byte[1];
        try(FileInputStream fin=new FileInputStream(String.format("C:\\img\\%s.jpg", id)))
        {
            data = new byte[fin.available()];
            // считываем буфер
            fin.read(data, 0, data.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, headers);
    }


}
