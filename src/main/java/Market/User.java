package Market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class User {
    private final String username;
    private String password;
    private String email;
    private Date birthDay;
    private String address;
    private int credit;

    private final ArrayList<Integer> buyList = new ArrayList<>();
    private final ArrayList<Integer> purchasedList = new ArrayList<>();

    public User(String username, String password, String email, Date birthDay, String address, int credit) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDay = birthDay;
        this.address = address;
        this.credit = credit;
    }

    void updateUser(String password, String email, Date birthDay, String address, int credit) {
        this.password = password;
        this.email = email;
        this.birthDay = birthDay;
        this.address = address;
        this.credit = credit;
    }

    public void purchaseCommodity(int commodityId) throws Exception {
        for (int buyListCommodityId : buyList) {
            if (buyListCommodityId == commodityId) {
                Commodity commodity = MarketManager.getInstance().getCommodityById(commodityId);
                if (commodity.getPrice() > credit) {
                    throw new Exception("Not enough credit to buy");
                }
                if (commodity.getInStock() <= 0) {
                    throw new Exception("Out of stock");
                }
                buyList.remove(Integer.valueOf(commodityId));
                purchasedList.add(commodityId);
                credit -= commodity.getPrice();
                commodity.buy();
            }
        }
        throw new Exception("First add item fo buyList");
    }

    public void addToBuyList(int commodityId) throws Exception {
        for (int buyListCommodityId : buyList) {
            if (buyListCommodityId == commodityId) {
                throw new Exception("Item already exist in buyList");
            }
        }
        buyList.add(commodityId);
    }

    public void removeFromBuyList(int commodityId) throws Exception {
        for (Integer buyListCommodityId : buyList) {
            if (buyListCommodityId == commodityId) {
                buyList.remove(buyListCommodityId);
                return;
            }
        }
        throw new Exception("Item not found");
    }

    List<Integer> getBuyList() {
        return Collections.unmodifiableList(buyList);
    }

    List<Integer> getPurchasedList() {
        return Collections.unmodifiableList(purchasedList);
    }

    String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getAddress() {
        return address;
    }

    public int getCredit() {
        return credit;
    }

    public void addCredit(int credit) {
        this.credit += credit;
    }
}
