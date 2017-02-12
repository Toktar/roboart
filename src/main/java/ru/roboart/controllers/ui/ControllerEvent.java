package ru.roboart.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roboart.models.Event;
import ru.roboart.repositories.EventRepository;

/*
 * Created by toktar.

*/

@Controller
@RequestMapping("/ui/event")
public class ControllerEvent extends ControllerForView<Event> {


    private String name = "event";
    private String entityTitle = "События";

    @Autowired
    public void config(EventRepository categoryRepository) {
        repository = categoryRepository;
    }

    @Override
    protected String getTitle(Event entity) {
        return entity.getTitle();
    }

    @Override
    protected String getId(Event entity) {
        return String.valueOf(entity.getId());
    }

    @Override
    protected String getName() {
        return name;
    }

    @Override
    protected String getEntityTitle() {
        return entityTitle;
    }


    @RequestMapping("/edit")
    public String greeting(Model model,
                           @RequestParam(value = "operation", required = false, defaultValue = "") String operation,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "displayedTime", required = false) String displayedTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "map", required = false) String map,
                           @RequestParam(value = "location", required = false) String location
    ) {


        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setDisplayedTime(displayedTime);
        if (startTime != null)
            event.setStartTime(Long.parseLong(startTime));
        if (endTime != null)
            event.setEndTime(Long.parseLong(endTime));
        event.setLocation(location);
        event.setLocation(map);

        if (id != null && !id.isEmpty()) {
            event.setId(Long.parseLong(id));
            if (!validate(title, description, displayedTime, endTime, startTime, location)) {
                Event dbEvent = repository.findOne(Long.parseLong(id));
                title = dbEvent.getTitle();
                description = dbEvent.getDescription();
                displayedTime = dbEvent.getDisplayedTime();
                endTime = dbEvent.getEndTime().toString();
                startTime = dbEvent.getStartTime().toString();
                location = dbEvent.getLocation();
            }
        }
        Event savedEvent = saveEntity(operation, event, model, validate(title, description, displayedTime, endTime, startTime, location));
        if (savedEvent != null) {
            model.addAttribute("id", savedEvent.getId());
        }
        StringBuilder fields = new StringBuilder();
        fields.append(fieldsDrawerService.generateTextbox("название", "title", title == null ? "" : title));
        fields.append(fieldsDrawerService.generateTextArea("Описание", "description", description == null ? "" : description));
        fields.append(fieldsDrawerService.generateTextbox("время старта - конца мероприятия ", "displayedTime", displayedTime == null ? "" : displayedTime));
        fields.append(fieldsDrawerService.generateDataBox("времени старта мероприятия", "startTime", startTime == null ? "" : startTime));
        fields.append(fieldsDrawerService.generateDataBox("времени окончания мероприятия", "endTime", endTime == null ? "" : endTime));
        fields.append(fieldsDrawerService.generateTextbox("место проведения", "location", location == null ? "" : location));
        fields.append(fieldsDrawerService.generateTextbox("карта места", "map", map == null ? "" : map));
        model.addAttribute("fields", fields.toString());
        model.addAttribute("entity", getName());

        return "edit";
    }


    private boolean validate(String title, String color) {
        return title != null && color != null;
    }

    private boolean validate(String title, String color, String id) {
        return title != null && color != null && id != null && !id.isEmpty();
    }

}
