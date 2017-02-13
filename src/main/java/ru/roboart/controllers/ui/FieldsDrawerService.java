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
        return " <li>\n" +
                "                <label class=\"description\" for=\"" + name + "\">" + title + " </label>\n" +
                "                <div>\n" +
                "                    <input id=\"" + name + "\" name=\"" + name + "\" class=\"element text medium\" type=\"text\" maxlength=\"255\" value=\"" + value + "\"/>\n" +
                "                </div>\n" +
                "            </li>";
    }

    public String generateColorBox(String title, String name, String value) {
        return " <li>\n" +
                "                <label class=\"description\" for=\"" + name + "\">" + title + " </label>\n" +
                "                <div>\n" +
                "                    <input id=\"" + name + "\" name=\"" + name + "\" class=\"element text medium\" type=\"color\" value=\"" + value + "\"/>\n" +
                "                </div>\n" +
                "            </li>";
    }


    public String generateDataBox(String title, String name, String value) {
        return " <li>\n" +
                "                <label class=\"description\" for=\"" + name + "\">" + title + " </label>\n" +
                "                <div>\n" +
                "                    <input id=\"" + name + "\" class=\"data_view\" name=\"view_" + name + "\" class=\"element text medium\" type=\"datetime-local\" value=\"\" onchange=\"converterToTimestamp(this.value,this.id);\" />\n" +
                "                    <input id=\"timestamp_" + name + "\" class=\"data\" name=\"" + name + "\" type=\"hidden\" value=\"" + value + "\" onchange=\"converterToData(this.value,this.name);\" />\n" +
                "                </div>\n" +
                "            </li>";
    }

    public String generateFileBox(String title, String name, String value) {
        String image = "";
        if (value != null && !value.isEmpty()) {
            image = "<br/>Текущее изображение:                      <input id=\"" + name + "\" name=\"" + name + "\" class=\"element text medium\" type=\"text\" maxlength=\"255\" value=\"" + value + "\"/>\n" + "<br/><img src=\"" + value + "\"/>";
        }

        return " <li>\n                <label class=\"description\" for=\"" + name + "\">" + title + " </label>\n" + "                <div>\n" + "                    <input id=\"file\" name=\"file\" class=\"element text medium\" type=\"file\" />\n" + image + "                </div>\n" + "            </li>";
    }


    public String generateTextArea(String title, String name, String value) {
        return " <li>\n" +
                "                <label class=\"description\" for=\"" + name + "\">" + title + " </label>\n" +
                "                <div>\n" +
                "                    <input id=\"" + name + "\" name=\"" + name + "\" class=\"element text medium\" type=\"textarea\" value=\"" + value + "\"/>\n" +
                "                </div>\n" +
                "            </li>";
    }

    public String generateOrgCategoryList(String title, String name, String value) {
        return " <li>\n" +
                "                <label class=\"description\" for=\"" + name + "\">" + title + " </label>\n" +
                "                <div>\n" +
                "               <select id=\"" + name + "\" name=\"" + name + "\" class=\"element text medium\" type=\"textarea\" value=\"" + value + "\">\n" +
                "                    <option " + ("organizers".equals(value) ? "selected=\"selected\"" : "") + ">organizers</option>\n" +
                "                   <option>partners</option>\n" +
                "               </select>" +
                "                </div>\n" +
                "            </li>";
    }


    public String generateItemInList(String itemTitle, String entityName, String itemId) {
        return " <li>\n" +
                "            <p>" + itemTitle + "</p> <br/>\n" +
                "            <a href=\"/ui/" + entityName + "/edit/?operation=edit&id=" + itemId + "\">Редактировать</a>\n" +
                "            &nbsp;&nbsp;&nbsp;<a href=\"?operation=del&id=" + itemId + "\">Удалить</a>\n" +
                "        </li>";
    }


}
