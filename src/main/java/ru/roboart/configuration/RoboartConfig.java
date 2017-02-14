package ru.roboart.configuration;

import org.springframework.stereotype.Controller;

/**
 * Created by toktar.
 */



@Controller
public class RoboartConfig {

    private String appToken = "d2b13b01-ffe1-43bb-854c-e024f3840e8d";
    private String password = "123qweasd";

    public String getAppToken() {
        return appToken;
    }

    public String getPassword() {
        return password;
    }
}
