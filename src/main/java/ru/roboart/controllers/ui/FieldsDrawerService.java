package ru.roboart.controllers.ui;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by toktar.
 */

@Service
public class FieldsDrawerService {
    Map<String, String> fieldNameMap = new HashMap();

    public String generateTextbox(String title, String name, String value) {
        return " <li id=\"li_1\" >\n" +
                "                <label class=\"description\" for=\""+name+"\">"+ title+" </label>\n" +
                "                <div>\n" +
                "                    <input id=\""+name+"\" name=\""+name+"\" class=\"element text medium\" type=\"text\" maxlength=\"255\" value=\""+value+"\"/>\n" +
                "                </div>\n" +
                "            </li>";
    }

    public String generateItemInList(String itemTitle, String entityName, String itemId) {
        return " <li>\n" +
                "            <p>" + itemTitle+ "</p> <br/>\n" +
                "            <a href=\"/ui/" + entityName+ "/edit/?operation=edit&id=" + itemId+ "\">Редактировать</a>\n" +
                "            &nbsp;&nbsp;&nbsp;<a href=\"?operation=del&id=" + itemId+ "\">Удалить</a>\n" +
                "        </li>";
    }


}
