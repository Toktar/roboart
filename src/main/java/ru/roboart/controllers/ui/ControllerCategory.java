package ru.roboart.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roboart.models.Category;
import ru.roboart.repositories.CategoryRepository;

/**
 * Created by toktar.
 */

@Controller
@RequestMapping("/ui/category")
public class ControllerCategory extends ControllerForView<Category>{


    private String name = "category";
    private String entityTitle = "Категории событий";

    @Autowired
    public void config(CategoryRepository categoryRepository) {
        repository = categoryRepository;
    }

    @Override
    protected String getTitle(Category entity) {
        return entity.getTitle();
    }

    @Override
    protected String getId(Category entity) {
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
        Category category = new Category();
        category.setTitle(title);
        category.setHexColor(color);
        if(id!=null && !id.isEmpty()) {
            category.setId(Long.parseLong(id));
            if(!validate(title, color)) {
                Category dbCategory = repository.findOne(Long.parseLong(id));
                title = dbCategory.getTitle();
                color = dbCategory.getHexColor();
            }
        }
        Category savedCategory = saveEntity(operation, category, model, validate(title,color));
        if(savedCategory!=null) {
            model.addAttribute("id", savedCategory.getId());
        }
        StringBuilder fields = new StringBuilder();
        fields.append(fieldsDrawerService.generateTextbox("название","title", title==null?"":title));
        fields.append(fieldsDrawerService.generateColorBox("цвет", "color", color==null?"":color));

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
