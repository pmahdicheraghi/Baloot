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

    String getUsername() {
        return username;
    }

    void addToBuyList(int commodityId) throws Exception {
        for (int buyListCommodityId : buyList) {
            if (buyListCommodityId == commodityId) {
                throw new Exception("Item already exist in buyList");
            }
        }
        buyList.add(commodityId);
    }

    void removeFromBuyList(int commodityId) throws Exception {
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
}
