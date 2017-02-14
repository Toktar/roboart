package ru.roboart.controllers.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by toktar.
 */
@RestController
@RequestMapping("/img")
public class ImageRestController {

    @RequestMapping("/{id}.{type}")
    @ResponseBody
    public HttpEntity<byte[]> getArticleImage(@PathVariable String id, @PathVariable String type) throws IOException {

//        logger.info("Requested picture >> " + id + " <<");

        // 1. download img from http://internal-picture-db/id.jpg ...
        byte[] data = new byte[1];
        try(FileInputStream fin=new FileInputStream(String.format("img"+ File.separator + "%s.%s", id, type)))
        {
            data = new byte[fin.available()];
            // считываем буфер
            fin.read(data, 0, data.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, headers);
    }

}
