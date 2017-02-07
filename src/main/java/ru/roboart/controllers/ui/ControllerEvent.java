/*
package ru.roboart.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roboart.models.Event;
import ru.roboart.models.Event;
import ru.roboart.repositories.EventRepository;

*/
/**
 * Created by toktar.
 *//*


@Controller
@RequestMapping("/ui/event")
public class ControllerEvent extends ControllerForView<Event>{


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
                           @RequestParam(value="operation", required=false, defaultValue="") String operation,
                           @RequestParam(value="title", required=false) String title,
                           @RequestParam(value="color", required=false) String color,
                           @RequestParam(value="id", required=false) String id
    ) {
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setDisplayedTime(displayedTime);
        event.setEndTime(Long.parseLong(endTime));
        event.setStartTime(Long.parseLong(startTime));
        event.setLocation(location);

        if(id!=null && !id.isEmpty()) {
            event.setId(Long.parseLong(id));
            if(!validate(title, color)) {
                Event dbEvent = repository.findOne(Long.parseLong(id));
                title = dbEvent.getTitle();
                color = dbEvent.getHexColor();
            }
        }
        Event savedEvent = saveEntity(operation, event, model, validate(title,color));
        if(savedEvent!=null) {
            model.addAttribute("id", savedEvent.getId());
        }
        StringBuilder fields = new StringBuilder();
        fields.append(fieldsDrawerService.generateTextbox("название","title", title==null?"":title));
        fields.append(fieldsDrawerService.generateTextbox("цвет", "color", color==null?"":color));

        model.addAttribute("fields", fields.toString());
        model.addAttribute("entity", getName());

        return "edit";
    }



    private boolean validate(String title, String color) {
        return title!=null && color!=null;
    }
    private boolean validate(String title, String color, String id) {
        return title!=null && color!=null && id!=null && !id.isEmpty();
    }

}
*/
