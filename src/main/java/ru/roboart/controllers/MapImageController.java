package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.Event;
import ru.roboart.models.MapImage;
import ru.roboart.repositories.MapImageRepository;

import java.util.Date;

/**
 * Created by Kida on 06.01.2017.
 */

@RestController
@RequestMapping("/map")
public class MapImageController extends MainRestController<MapImage> {

    @Autowired
    private MapImageRepository mapRepository;

   @Autowired
    public MapImageController mapImageController() {
        MapImageController mapImageController = new MapImageController();
       this.repository = mapRepository;
        mapImageController.repository = mapRepository;
       mapImageController.lastUpdate = new Date();
       this.lastUpdate = new Date();
        return mapImageController;
    }
}
