package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.Event;
import ru.roboart.repositories.EventRepository;

import javax.ws.rs.HeaderParam;
import java.util.List;

/**
 * Created by Kida on 06.01.2017.
 */

@RestController
@RequestMapping("/event")
public class EventController extends MainRestController<Event> {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    public EventController() {
        repository = eventRepository;
    }
}
