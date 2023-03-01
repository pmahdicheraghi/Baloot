package Market;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class JsonParser {
    static JSONObject parseJson(String s) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(s);
            return (JSONObject) obj;
        } catch (ParseException pe) {
            System.out.println(pe);
        }
        return new JSONObject();
    }

    static ArrayList<Category> parseCategory(String c) {
        c = c.replaceAll("[\\[\\]]", "");
        String[] spited = c.split(", ");
        ArrayList<Category> categories = new ArrayList<>();
        for (String val : spited) {
            categories.add(Category.valueOf(val));
        }
        return categories;
    }
}

