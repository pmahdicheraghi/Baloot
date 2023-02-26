package Market;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Commodity {
    private int id;
    private String name;
    private int price;
    private float rating;
    private int inStock;
    private ArrayList<Category> categories;

    public Commodity(int id, String name, int price, ArrayList<Category> categories, float rating, int inStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.inStock = inStock;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("name", name);
        obj.put("price", price);
        obj.put("categories", categories.toString());
        obj.put("rating", rating);
        obj.put("inStock", inStock);
        return obj;
    }
}
