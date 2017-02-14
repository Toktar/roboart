package ru.roboart.controllers.ui;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.roboart.controllers.utils.UserController;

import javax.ws.rs.core.Context;

/**
 * Created by toktar.
 */

@Controller
@RequestMapping("/user")
public class AutorizationController {


    @Autowired
    private UserController userController;

    @RequestMapping(value ="/login")
    public String login(Model model,
                        @Context HttpServletRequest requestContext,
                        @RequestParam(value="password", required=false, defaultValue="") String password) {
        if(password!=null && !password.isEmpty()) {
            if(userController.login(requestContext.getRemoteAddr(), password)) {
                return "allready_autorize";
            } else {
                model.addAttribute("message", "Неправильно введён пароль");
            }
        }
            return "autorize";


    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        userController.logout();
        return "allready_autorize";
    }
}
