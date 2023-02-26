package Market;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class MarketManager {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Provider> providers = new ArrayList<>();

    private static ArrayList<Commodity> commodities = new ArrayList<>();

    static ReturnObject addUser(String username, String password, String email, Date birthDay, String address, int credit) {
        CharSequence[] invalidChars = {" ", "‌", "!", "@", "#", "$", "%", "^", "&", "*"};
        for (CharSequence invalidChar : invalidChars) {
            if (username.contains(invalidChar)) {
                return new ReturnObject(false, "Invalid character in username");
            }
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.updateUser(password, email, birthDay, address, credit);
                return new ReturnObject(true, "User updated");
            }
        }
        User newUser = new User(username, password, email, birthDay, address, credit);
        users.add(newUser);
        return new ReturnObject(true, "User created");
    }

    static ReturnObject addProvider(int id, String name, Date registryDate) {
        for (Provider provider : providers) {
            if (provider.getId() == id) {
                return new ReturnObject(false, "This id is already registered");
            }
        }
        Provider newProvider = new Provider(id, name, registryDate);
        providers.add(newProvider);
        return new ReturnObject(true, "Provider added");
    }

    static ReturnObject addCommodity(int id, String name, int providerId, int price, ArrayList<Category> categories, float rating, int inStock) {
        for (Provider provider : providers) {
            if (provider.getId() == providerId) {
                for (Commodity commodity : commodities) {
                    if (commodity.getId() == id) {
                        return new ReturnObject(false, "This id is already registered");
                    }
                }
                Commodity newCommodity = new Commodity(id, name, price, categories, rating, inStock);
                commodities.add(newCommodity);
                return new ReturnObject(true, "Commodity added");
            }
        }
        return new ReturnObject(false, "Provider id not found");
    }

    static ReturnObject getCommoditiesList() {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Commodity commodity : commodities) {
            arr.add(commodity.toJsonObject());
        }
        obj.put("commoditiesList", arr);
        return new ReturnObject(true, obj.toString());
    }


}
