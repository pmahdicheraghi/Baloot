package Market;

import java.util.*;

public class MarketManager {
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Provider> providers = new ArrayList<>();
    private final ArrayList<Commodity> commodities = new ArrayList<>();
    private final ArrayList<Rating> ratings = new ArrayList<>();

    private static MarketManager marketManagerInstance = null;

    private MarketManager() {
    }

    public void init() {
        // TODO: init data by api
        try {
            addUser("gholam", "xyz", "golam@gmail.com", new Date(), "xyz", 2);
            addUser("mahdi", "123", "pmch@gmail.com", new Date(), "xyz", 10);
            addProvider(1, "p1", new Date());
            addProvider(2, "p2", new Date());
            addCommodity(1, "c1", 1, 120, new ArrayList<>(Arrays.asList(Category.Technology, Category.Vegetables)), 2.2f, 4);
            addCommodity(2, "c2", 2, 120, new ArrayList<>(), 3.2f, 5);
            addCommodity(3, "c3", 1, 120, new ArrayList<>(Arrays.asList(Category.Technology)), 4.2f, 5);
            getUserByUsername("mahdi").addToBuyList(3);
        } catch (Exception e) {
            System.out.println(e);
        }
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

    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private Provider findProviderById(int id) {
        for (Provider provider : providers) {
            if (provider.getId() == id) {
                return provider;
            }
        }
        return null;
    }

    private Commodity findCommodityById(int id) {
        for (Commodity commodity : commodities) {
            if (commodity.getId() == id) {
                return commodity;
            }
        }
        return null;
    }

    public boolean addUser(String username, String password, String email, Date birthDay, String address, int credit) throws Exception {
        CharSequence[] invalidChars = {" ", "‌", "!", "@", "#", "$", "%", "^", "&", "*"};
        for (CharSequence invalidChar : invalidChars) {
            if (username.contains(invalidChar)) {
                throw new Exception("Invalid character in username");
            }
        }
        User user = findUserByUsername(username);
        if (user == null) {
            users.add(new User(username, password, email, birthDay, address, credit));
            return true;
        }
        user.updateUser(password, email, birthDay, address, credit);
        return true;
    }

    public boolean addProvider(int id, String name, Date registryDate) throws Exception {
        Provider provider = findProviderById(id);
        if (provider != null) {
            throw new Exception("This id is already registered");
        }
        providers.add(new Provider(id, name, registryDate));
        return true;
    }

    public boolean addCommodity(int id, String name, int providerId, int price, ArrayList<Category> categories, float rating, int inStock) throws Exception {
        Provider provider = findProviderById(providerId);
        if (provider == null) {
            throw new Exception("Provider id not found");
        }
        Commodity commodity = findCommodityById(id);
        if (commodity != null) {
            throw new Exception("This id is already registered");
        }
        commodities.add(new Commodity(id, name, providerId, price, categories, rating, inStock));
        return true;
    }

    public List<Commodity> getCommoditiesList() {
        return Collections.unmodifiableList(commodities);
    }

    public boolean rateCommodity(String username, int commodityId, int score) throws Exception {
        if (score < 1 || score > 10) {
            throw new Exception("Invalid score");
        }
        User user = findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        Commodity commodity = findCommodityById(commodityId);
        if (commodity == null) {
            throw new Exception("Commodity not found");
        }
        for (Rating rating : ratings) {
            if (rating.getCommodityId() == commodityId && rating.getUsername().equals(username)) {
                rating.updateScore(score);
                updateCommodityScore(commodityId);
                return true;
            }
        }
        ratings.add(new Rating(username, commodityId, score));
        updateCommodityScore(commodityId);
        return true;
    }

    public boolean addToBuyList(String username, int commodityId) throws Exception {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        Commodity commodity = findCommodityById(commodityId);
        if (commodity == null) {
            throw new Exception("Commodity not found");
        }
        if (commodity.getInStock() <= 0) {
            throw new Exception("Out of stoke");
        }
        user.addToBuyList(commodityId);
        return true;
    }

    public boolean removeFromBuyList(String username, int commodityId) throws Exception {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        Commodity commodity = findCommodityById(commodityId);
        if (commodity == null) {
            throw new Exception("Commodity not found");
        }
        user.removeFromBuyList(commodityId);
        return true;
    }

    public Commodity getCommodityById(int id) throws Exception {
        Commodity commodity = findCommodityById(id);
        if (commodity == null) {
            throw new Exception("Commodity not found");
        }
        return commodity;
    }

    public Provider getProviderById(int id) throws Exception {
        Provider provider = findProviderById(id);
        if (provider == null) {
            throw new Exception("Provider not found");
        }
        return provider;
    }

    public User getUserByUsername(String username) throws Exception {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    public List<Commodity> getCommoditiesByCategory(Category category) {
        List<Commodity> temp = new ArrayList<>();
        for (Commodity commodity : commodities) {
            if (commodity.getCategories().contains(category)) {
                temp.add(commodity);
            }
        }
        return temp;
    }

    public List<Commodity> getBuyList(String username) throws Exception {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        List<Integer> buyList = user.getBuyList();
        ArrayList<Commodity> commodityArrayList = new ArrayList<>();
        for (int buyItem : buyList) {
            commodityArrayList.add(getCommodityById(buyItem));
        }
        return Collections.unmodifiableList(commodityArrayList);
    }

    public List<Commodity> getPurchasedList(String username) throws Exception {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        List<Integer> purchasedList = user.getPurchasedList();
        ArrayList<Commodity> commodityArrayList = new ArrayList<>();
        for (int buyItem : purchasedList) {
            commodityArrayList.add(getCommodityById(buyItem));
        }
        return Collections.unmodifiableList(commodityArrayList);
    }

    public void addCreditToUser(String username, int credit) throws Exception {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        user.addCredit(credit);
    }

}
