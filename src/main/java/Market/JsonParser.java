package Market;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class JsonParser {
    static JSONObject parseJsonObject(String s) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(s);
            return (JSONObject) obj;
        } catch (ParseException pe) {
            System.out.println(pe);
        }
        return new JSONObject();
    }

    static JSONArray parseJsonArray(String s) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(s);
            return (JSONArray) obj;
        } catch (ParseException pe) {
            System.out.println(pe);
        }
        return new JSONArray();
    }

    static ArrayList<Category> parseCategory(String c) {
        c = c.replaceAll("[\\[\\]]", "");
        String[] spited = c.split(", ");
        ArrayList<Category> categories = new ArrayList<>();
        for (String val : spited) {
            categories.add(Category.get(val));
        }
        return categories;
    }

    static ArrayList<Category> parseCategory(JSONArray a) {
        ArrayList<Category> categories = new ArrayList<>();
        for (Object obj : a) {
            String val = (String) obj;
            categories.add(Category.get(val));
        }
        return categories;
    }
}

