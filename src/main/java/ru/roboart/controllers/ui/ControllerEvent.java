package ru.roboart.controllers.ui;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.roboart.controllers.utils.FileController;
import ru.roboart.models.Category;
import ru.roboart.models.Event;
import ru.roboart.repositories.CategoryRepository;
import ru.roboart.repositories.EventRepository;

import javax.ws.rs.core.Context;
import java.text.SimpleDateFormat;
import java.util.List;

/*
 * Created by toktar.

*/

@Controller
@RequestMapping("/ui/event")
public class ControllerEvent extends ControllerForView<Event> {


    private String name = "event";
    private String entityTitle = "События";

    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    FileController fileController;

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

                           @Context HttpServletRequest requestContext,
                           @RequestParam(value = "operation", required = false, defaultValue = "") String operation,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "displayedTime", required = false) String displayedTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "map", required = false) String map,
                           @RequestParam(value = "location", required = false) String location,
                           @RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "file", required = false) MultipartFile file

    ) {
        if(!isAuth(requestContext.getRemoteAddr())) return "redirect:/user/login";
        id = validating(id);
        if (file != null) {
            map = fileController.uploadFile(file);
        }
        Event event = new Event();
        if (id != null && !id.isEmpty()) {
            event.setId(Long.parseLong(id));
            if (!validate(title, description, displayedTime, endTime, startTime, location)) {
                Event dbEvent = repository.findOne(Long.parseLong(id));
                if (title == null) title = dbEvent.getTitle();
                if (description == null) description = dbEvent.getDescription();
                if (displayedTime == null) displayedTime = dbEvent.getDisplayedTime();
                if (endTime == null) endTime = dbEvent.getEndTime().toString();
                if (startTime == null) startTime = dbEvent.getStartTime().toString();
                if (location == null) location = dbEvent.getLocation();
                if (category == null) category = String.valueOf(dbEvent.getCategory().getId());

            }
        }
        if (startTime != null && endTime != null) {


            displayedTime = new SimpleDateFormat("HH:mm").format(Long.parseLong(startTime)) + " - "
                    + new SimpleDateFormat("HH:mm").format(Long.parseLong(endTime));
        }

        event.setTitle(title);
        event.setDescription(description);
        event.setDisplayedTime(displayedTime);
        if (startTime != null)
            event.setStartTime(Long.parseLong(startTime));
        if (endTime != null)
            event.setEndTime(Long.parseLong(endTime));
        if (category != null)
            event.setCategory(categoryRepository.findOne(Long.parseLong(category)));
        event.setLocation(location);
        event.setLocation(map);


        Event savedEvent = saveEntity(operation, event, model, validate(title, description, displayedTime, endTime, startTime, location, category));
        if (savedEvent != null) {
            model.addAttribute("id", savedEvent.getId());
        }
        StringBuilder fields = new StringBuilder();
        fields.append(fieldsDrawerService.generateTextbox("название", "title", title == null ? "" : title));
        fields.append(fieldsDrawerService.generateTextArea("Описание", "description", description == null ? "" : description));
        fields.append(fieldsDrawerService.generateNotModifLable("время старта - конца мероприятия ", "displayedTime", displayedTime == null ? "" : displayedTime));
        fields.append(fieldsDrawerService.generateDataBox("времени старта мероприятия", "startTime", startTime == null ? "" : startTime));
        fields.append(fieldsDrawerService.generateDataBox("времени окончания мероприятия", "endTime", endTime == null ? "" : endTime));
        fields.append(fieldsDrawerService.generateTextbox("место проведения", "location", location == null ? "" : location));
        fields.append(fieldsDrawerService.generateCategoryList("категория", "category", category == null ? "" : category, (List<Category>) categoryRepository.findAll()));
        fields.append(fieldsDrawerService.generateFileBox("карта места", "map", map == null ? "" : map));
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
