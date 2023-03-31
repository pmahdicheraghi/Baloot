package Market;

import java.util.ArrayList;

public class Discount {
    private String code;
    private int percent;

    private ArrayList<String> usedUsers = new ArrayList<>();

    public Discount(String code, int percent) {
        this.code = code;
        this.percent = percent;
    }

    public String getCode() {
        return code;
    }

    public int getPercent() {
        return percent;
    }

    public boolean canUse(String username) {
        for (String usedUser : usedUsers) {
            if (usedUser.equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void use(String username) throws RuntimeException {
        for (String usedUser : usedUsers) {
            if (usedUser.equals(username)) {
                throw new RuntimeException("User already used this discount");
            }
        }
        usedUsers.add(username);
    }

}
