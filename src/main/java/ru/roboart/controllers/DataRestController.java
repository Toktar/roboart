package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.MapImage;
import ru.roboart.repositories.MapImageRepository;

/**
 * Created by Kida on 06.01.2017.
 */
@RestController
public class DataRestController {

    @Autowired
    MapImageRepository mapImageRepository;

    @RequestMapping("/set")
    String set() {
        MapImage mapImage = new MapImage();

        mapImage.setImage("url");
        mapImage.setTitle("title Text");
        mapImageRepository.save(mapImage);
       return "ok";
    }
}
