package ru.roboart.controllers.ui;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.roboart.controllers.utils.FileController;
import ru.roboart.models.Organizer;
import ru.roboart.repositories.OrganizerRepository;

import javax.ws.rs.core.Context;

/**
 * Created by toktar.
 */

@Controller
@RequestMapping("/ui/organizer")
public class ControlleOrganizer extends ControllerForView<Organizer>{


    private String name = "organizer";
    private String entityTitle = "Организаторы";

    @Autowired
    FileController fileController;

    @Autowired
    public void config(OrganizerRepository organizerRepository) {
        repository = organizerRepository;
    }

    @Override
    protected String getTitle(Organizer entity) {
        return entity.getTitle();
    }

    @Override
    protected String getId(Organizer entity) {
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
                           @RequestParam(value="operation", required=false, defaultValue="") String operation,
                           @RequestParam(value="title", required=false) String title,
                           @RequestParam(value="icon", required=false) String icon,
                           @RequestParam(value="category", required=false) String category,
                           @RequestParam(value="urlToTransition", required=false) String urlToTransition,
                           @RequestParam(value="id", required=false) String id,
                           @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        id = validating(id);
        if (file != null) {
            icon = fileController.uploadFile(file);
        }
        Organizer organizer = new Organizer();

        if(id!=null && !id.isEmpty()) {
            organizer.setId(Long.parseLong(id));
            if(!validate(title, icon, category, urlToTransition)) {
                Organizer dbOrganizer = repository.findOne(Long.parseLong(id));
                if(dbOrganizer!=null) {
                    if (title == null) title = dbOrganizer.getTitle();
                    if (icon == null) icon = dbOrganizer.getIcon();
                    if (category == null) category = dbOrganizer.getCategory();
                    if (urlToTransition == null)  urlToTransition = dbOrganizer.getUrlToTransition();
                }

            }
        }
        organizer.setTitle(title);
        organizer.setCategory(category);
        organizer.setIcon(icon);
        organizer.setUrlToTransition(urlToTransition);
        Organizer savedOrganizer = saveEntity(operation, organizer, model, validate(title, icon, category, urlToTransition));
        if(savedOrganizer!=null) {
            model.addAttribute("id", savedOrganizer.getId());
        }
        StringBuilder fields = new StringBuilder();
        fields.append(fieldsDrawerService.generateTextbox("название","title", title==null?"":title));
        fields.append(fieldsDrawerService.generateFileBox("логотип","icon", icon==null?"":icon));
        fields.append(fieldsDrawerService.generateOrgCategoryList("категория","category", category==null?"":category));
        fields.append(fieldsDrawerService.generateTextbox("ссылка для перехода","urlToTransition", urlToTransition==null?"":urlToTransition));

        model.addAttribute("fields", fields.toString());
        model.addAttribute("entity", getName());

        return "edit";
    }



    private boolean validate(String title1, String s, String title, String icon) {
        return title!=null && icon!=null && title1!=null && s!=null;
    }
    private boolean validate(String title, String icon, String id) {
        return title!=null && icon!=null && id!=null && !id.isEmpty();
    }

}
