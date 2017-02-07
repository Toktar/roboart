package ru.roboart.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roboart.models.MapImage;
import ru.roboart.repositories.MapImageRepository;

/**
 * Created by toktar.
 */

@Controller
@RequestMapping("/ui/map")
public class ControllerMapImage extends ControllerForView<MapImage>{


    private String name = "map";
    private String entityTitle = "Категории событий";

    @Autowired
    public void config(MapImageRepository mapRepository) {
        repository = mapRepository;
    }

    @Override
    protected String getTitle(MapImage entity) {
        return entity.getTitle();
    }

    @Override
    protected String getId(MapImage entity) {
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
                           @RequestParam(value="image", required=false) String image,
                           @RequestParam(value="id", required=false) String id
    ) {
        MapImage map = new MapImage();
        map.setTitle(title);
        map.setImage(image); 
        if(id!=null && !id.isEmpty()) {
            map.setId(Long.parseLong(id));
            if(!validate(title, image)) {
                MapImage dbMapImage = repository.findOne(Long.parseLong(id));
                title = dbMapImage.getTitle();
                image = dbMapImage.getImage();
            }
        }
        MapImage savedMapImage = saveEntity(operation, map, model, validate(title,image));
        if(savedMapImage!=null) {
            model.addAttribute("id", savedMapImage.getId());
        }
        StringBuilder fields = new StringBuilder();
        fields.append(fieldsDrawerService.generateTextbox("название","title", title==null?"":title));
        fields.append(fieldsDrawerService.generateTextbox("ссылка на изображение ", "image", image==null?"":image));

        model.addAttribute("fields", fields.toString());
        model.addAttribute("entity", getName());

        return "edit";
    }



    private boolean validate(String title, String image) {
        return title!=null && image!=null;
    }
    private boolean validate(String title, String image, String id) {
        return title!=null && image!=null && id!=null && !id.isEmpty();
    }

}
