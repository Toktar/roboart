package ru.roboart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.roboart.models.Category;
import ru.roboart.repositories.CategoryRepository;

/**
 * Created by Kida on 06.01.2017.
 */

@RestController
@RequestMapping("/category")
public class CategoryController extends MainRestController<Category> {

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        repository = categoryRepository;
    }
}
