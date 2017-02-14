package ru.roboart.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.roboart.configuration.RoboartConfig;

/**
 * Created by toktar.
 */

@Controller
public class UserController {

    @Autowired
    private RoboartConfig config;

    private String ip = "";


    public boolean isAdmin(String newIp) {
        return ip.equals(newIp);
    }

    public boolean login(String ip, String password) {
        if (config.getPassword().equals(password)) {
            this.ip = ip;
            return true;
        }
        return false;
    }

    public void logout() {
        this.ip = "";
    }
}
