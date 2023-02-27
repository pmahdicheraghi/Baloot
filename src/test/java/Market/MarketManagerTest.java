package Market;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class MarketManagerTest {
    @Test
    public void testAddUser() {
        MarketManager mm = MarketManager.getInstance();
        ReturnObject obj1 = mm.addUser("mahdi", "xxxx", "pm@gmail.com", new Date(), "x", 0);
        assertTrue(obj1.success());
        ReturnObject obj2 = mm.addUser("mahdi", "yyyy", "pm@gmail.com", new Date(), "x", 0);
        assertTrue(obj2.success());
        ReturnObject obj3 = mm.addUser("mah@di", "yyyy", "pm@gmail.com", new Date(), "x", 0);
        assertFalse(obj3.success());
    }

    @Test
    public void testAddProvider() {
        MarketManager mm = MarketManager.getInstance();
        ReturnObject obj1 = mm.addProvider(1, "mahdi", new Date());
        assertTrue(obj1.success());
        ReturnObject obj2 = mm.addProvider(1, "mahdi", new Date());
        assertFalse(obj2.success());
        ReturnObject obj3 = mm.addProvider(2, "mahdi", new Date());
        assertTrue(obj3.success());
    }

    @Test
    public void testAddCommodity() {
        MarketManager mm = MarketManager.getInstance();
        ReturnObject obj1 = mm.addCommodity(1, "mahdi", 3, 100, new ArrayList<Category>(), 8.2f, 10);
        assertFalse(obj1.success());
        mm.addProvider(3, "mahdi", new Date());
        ReturnObject obj2 = mm.addCommodity(1, "mahdi", 3, 100, new ArrayList<Category>(), 8.2f, 10);
        assertTrue(obj2.success());
        ReturnObject obj3 = mm.addCommodity(1, "mahdi", 3, 100, new ArrayList<Category>(), 8.2f, 10);
        assertFalse(obj3.success());
    }

    @Test
    public void testGetCommoditiesList() {
        MarketManager mm = MarketManager.getInstance();
        mm.addProvider(4, "mahdi", new Date());
        ReturnObject obj1 = mm.addCommodity(
                4,
                "mahdi",
                4,
                100,
                new ArrayList<Category>(Arrays.asList(Category.Phone, Category.Technology)),
                8.2f,
                10);
        assertTrue(obj1.success());
        System.out.println(mm.getCommoditiesList());
    }
}
