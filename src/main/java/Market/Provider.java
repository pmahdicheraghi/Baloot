package Market;

import java.util.Date;

public class Provider {
    private int id;
    private String name;
    private Date registryDate;

    public Provider(int id, String name, Date registryDate) {
        this.id = id;
        this.name = name;
        this.registryDate = registryDate;
    }

    public int getId() {
        return id;
    }
}
