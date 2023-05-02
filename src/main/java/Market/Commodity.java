package Market;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Commodity {
    private final int id;
    private final String name;
    private final int providerId;
    private final int price;
    private float rating;
    private int inStock;
    private final ArrayList<Category> categories;

    private final String imageUrl;
    public Commodity(int id, String name, int providerId, int price, ArrayList<Category> categories, float rating, int inStock,String imageUrl) {
        this.id = id;
        this.name = name;
        this.providerId = providerId;
        this.price = price;
        this.rating = rating;
        this.inStock = inStock;
        this.categories = categories;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public int getProviderId() {
        return providerId;
    }

    public int getInStock() {
        return inStock;
    }

    public void updateRating(float rating) {
        this.rating = rating;
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public void buy() throws RuntimeException {
        if (inStock == 0) {
            throw new RuntimeException("Not enough in stock");
        }
        inStock--;
    }

    public JSONObject toJsonObject(boolean withInStock) {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("name", name);
        obj.put("price", price);
        obj.put("categories", categories.toString());
        obj.put("rating", rating);
        if (withInStock) obj.put("inStock", inStock);
        return obj;
    }

}
