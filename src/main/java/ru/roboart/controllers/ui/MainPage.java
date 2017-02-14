package ru.roboart.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by toktar.
 */

@Controller
@RequestMapping("/ui")
public class MainPage {
    @RequestMapping("/")
    public String greeting(Model model) {
        return "index";
    }
}
