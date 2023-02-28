package Market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MarketManager {
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Provider> providers = new ArrayList<>();
    private final ArrayList<Commodity> commodities = new ArrayList<>();
    private final ArrayList<Rating> ratings = new ArrayList<>();

    private static MarketManager marketManagerInstance = null;

    private MarketManager() {
    }

    public static MarketManager getInstance() {
        if (marketManagerInstance == null)
            marketManagerInstance = new MarketManager();

        return marketManagerInstance;
    }

    private void updateCommodityScore(int commodityId) {
        int sumRating = 0;
        int numRating = 0;
        for (Rating rating : ratings) {
            if (rating.getCommodityId() == commodityId) {
                sumRating += rating.getScore();
                numRating++;
            }
        }
        if (numRating != 0) {
            for (Commodity commodity : commodities) {
                if (commodity.getId() == commodityId) {
                    commodity.updateRating(((float) sumRating) / numRating);
                    return;
                }
            }
        }
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
                return true;
            }
        }
        users.add(new User(username, password, email, birthDay, address, credit));
        return true;
    }

    boolean addProvider(int id, String name, Date registryDate) throws Exception {
        for (Provider provider : providers) {
            if (provider.getId() == id) {
                throw new Exception("This id is already registered");
            }
        }
        providers.add(new Provider(id, name, registryDate));
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
                commodities.add(new Commodity(id, name, price, categories, rating, inStock));
                return true;
            }
        }
        throw new Exception("Provider id not found");
    }

    List<Commodity> getCommoditiesList() {
        return Collections.unmodifiableList(commodities);
    }

    boolean rateCommodity(String username, int commodityId, int score) throws Exception {
        if (score < 1 || score > 10) {
            throw new Exception("Invalid score");
        }
        for (User user : users) {
            if (user.getUsername() == username) {
                for (Commodity commodity : commodities) {
                    if (commodity.getId() == commodityId) {
                        for (Rating rating : ratings) {
                            if (rating.getCommodityId() == commodityId && rating.getUsername() == username) {
                                rating.updateScore(score);
                                updateCommodityScore(commodityId);
                                return true;
                            }
                        }
                        ratings.add(new Rating(username, commodityId, score));
                        updateCommodityScore(commodityId);
                        return true;
                    }
                }
                throw new Exception("Commodity not found");
            }
        }
        throw new Exception("User not found");
    }

    boolean addToBuyList(String username, int commodityId) throws Exception {
        for (User user : users) {
            if (user.getUsername() == username) {
                for (Commodity commodity : commodities) {
                    if (commodity.getId() == commodityId) {
                        if (commodity.getInStock() > 0) {
                            user.addToBuyList(commodityId);
                        }
                        throw new Exception("Out of stoke");
                    }
                }
                throw new Exception("Commodity not found");
            }
        }
        throw new Exception("User not found");
    }
}
