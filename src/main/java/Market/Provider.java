package Market;

import java.util.Date;

public class Provider {
    private final int id;
    private final String name;
    private final Date registryDate;

    public Provider(int id, String name, Date registryDate) {
        this.id = id;
        this.name = name;
        this.registryDate = registryDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getRegistryDate() {
        return registryDate;
    }
}
