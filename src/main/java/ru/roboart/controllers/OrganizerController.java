package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.Organizer;
import ru.roboart.repositories.OrganizerRepository;

/**
 * Created by Kida on 06.01.2017.
 */

@RestController
@RequestMapping("/organizer")
public class OrganizerController extends MainRestController<Organizer> {

    @Autowired
    public OrganizerController(OrganizerRepository organizerRepository) {
        repository = organizerRepository;
    }
}
