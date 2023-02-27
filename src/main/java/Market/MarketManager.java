package Market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MarketManager {
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Provider> providers = new ArrayList<>();
    private final ArrayList<Commodity> commodities = new ArrayList<>();

    private static MarketManager marketManagerInstance = null;

    private MarketManager() {}

    public static MarketManager getInstance() {
        if (marketManagerInstance == null)
            marketManagerInstance = new MarketManager();

        return marketManagerInstance;
    }


    boolean addUser(String username, String password, String email, Date birthDay, String address, int credit) throws Exception {
        CharSequence[] invalidChars = {" ", "‌", "!", "@", "#", "$", "%", "^", "&", "*"};
        for (CharSequence invalidChar : invalidChars) {
            if (username.contains(invalidChar)) {
                throw new Exception("Invalid character in username");
            }
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.updateUser(password, email, birthDay, address, credit);
                throw new Exception("User updated");
            }
        }
        User newUser = new User(username, password, email, birthDay, address, credit);
        users.add(newUser);
        return true;
    }

    boolean addProvider(int id, String name, Date registryDate) throws Exception {
        for (Provider provider : providers) {
            if (provider.getId() == id) {
                throw  new Exception("This id is already registered");
            }
        }
        Provider newProvider = new Provider(id, name, registryDate);
        providers.add(newProvider);
        return true;
    }

    boolean addCommodity(int id, String name, int providerId, int price, ArrayList<Category> categories, float rating, int inStock) throws Exception {
        for (Provider provider : providers) {
            if (provider.getId() == providerId) {
                for (Commodity commodity : commodities) {
                    if (commodity.getId() == id) {
                        throw new Exception("This id is already registered");
                    }
                }
                Commodity newCommodity = new Commodity(id, name, price, categories, rating, inStock);
                commodities.add(newCommodity);
                return true;
            }
        }
        throw new Exception("Provider id not found");
    }

    List<Commodity> getCommoditiesList() {
        return Collections.unmodifiableList(commodities);
    }
}
