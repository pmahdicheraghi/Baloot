package Market;

import java.util.Date;

class User {
    private String username;
    private String password;
    private String email;
    private Date birthDay;
    private String address;
    private int credit;

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
}
