package ru.roboart.controllers.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roboart.controllers.MainRestController;

import java.util.Date;
import java.util.List;

/**
 * Created by toktar.
 */
public abstract class ControllerForView<T> {


/*    @RequestMapping("/edit")
    public String greeting(@RequestParam(value="operation", required=false, defaultValue="new") String operation,
                           @RequestParam(value="status", required=false, defaultValue="status") String status,
                           @RequestParam(value="operation", required=false, defaultValue="new") String url,
                           @RequestParam(value="operation", required=false, defaultValue="new") String fields,
                           Model model) {
        model.addAttribute("operation", operation);
        return "greeting";
    }*/

/*
    @RequestMapping("/list1")
    public String greeting(Model model) {

        model.addAttribute("operation", "Редактирование");
        model.addAttribute("status", "Сохранено");
        String fields;
        model.addAttribute("fields", "fields");
        return "greeting";
    }
*/
    protected PagingAndSortingRepository<T, Long> repository;

    @Autowired
    protected FieldsDrawerService fieldsDrawerService;

    @Autowired
    protected MainRestController<T> controller;


    @RequestMapping("/list")
    public String greeting(Model model,
                           @RequestParam(value="operation", required=false, defaultValue="") String operation,
                           @RequestParam(value="id", required=false, defaultValue="") String itemId) {


        if(operation.equals("del") && !itemId.isEmpty()){
            repository.delete(Long.parseLong(itemId));
            controller.setLastUpdate(new Date());
        }

        List<T> entityList =  (List<T>)repository.findAll();
        StringBuilder list = new StringBuilder();
        for (T entity : entityList) {
            list.append(fieldsDrawerService.generateItemInList(getTitle(entity), getName(), getId(entity)));
        }

        model.addAttribute("entity_title", getEntityTitle());
        model.addAttribute("entity_name", getName());
        model.addAttribute("list", list.toString());
        return "list";
    }

    protected T saveEntity(String operation, T entity, Model model, boolean isValidate) {
        T res = null;
        if(isValidate) {
            res = repository.save(entity);
            model.addAttribute("status", "Сохранено - "+new Date().toString());
            controller.setLastUpdate(new Date());
        }
        if(operation.equals("add") && isValidate) {
            model.addAttribute("operation", "Добавление");
        } else {
            model.addAttribute("operation", "Редактирование");
        }
        return res;
    }

    protected abstract String getTitle(T entity);
    protected abstract String getId(T entity);
    protected abstract String  getName();
    protected abstract String  getEntityTitle();

}
