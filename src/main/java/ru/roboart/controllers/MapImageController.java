package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.MapImage;
import ru.roboart.repositories.MapImageRepository;

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


}
