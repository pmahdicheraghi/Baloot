package Market;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class MarketManagerTest {
    @Test
    public void testAddUser() {
        ReturnObject obj1 = MarketManager.addUser("mahdi", "xxxx", "pm@gmail.com", new Date(), "x", 0);
        assertTrue(obj1.success());
        ReturnObject obj2 = MarketManager.addUser("mahdi", "yyyy", "pm@gmail.com", new Date(), "x", 0);
        assertTrue(obj2.success());
        ReturnObject obj3 = MarketManager.addUser("mah@di", "yyyy", "pm@gmail.com", new Date(), "x", 0);
        assertFalse(obj3.success());
    }

    @Test
    public void testAddProvider() {
        ReturnObject obj1 = MarketManager.addProvider(1, "mahdi", new Date());
        assertTrue(obj1.success());
        ReturnObject obj2 = MarketManager.addProvider(1, "mahdi", new Date());
        assertFalse(obj2.success());
        ReturnObject obj3 = MarketManager.addProvider(2, "mahdi", new Date());
        assertTrue(obj3.success());
    }

    @Test
    public void testAddCommodity() {
        ReturnObject obj1 = MarketManager.addCommodity(1, "mahdi", 3, 100, new ArrayList<Category>(), 8.2f, 10);
        assertFalse(obj1.success());
        MarketManager.addProvider(3, "mahdi", new Date());
        ReturnObject obj2 = MarketManager.addCommodity(1, "mahdi", 3, 100, new ArrayList<Category>(), 8.2f, 10);
        assertTrue(obj2.success());
        ReturnObject obj3 = MarketManager.addCommodity(1, "mahdi", 3, 100, new ArrayList<Category>(), 8.2f, 10);
        assertFalse(obj3.success());
    }

    @Test
    public void testGetCommoditiesList() {
        MarketManager.addProvider(4, "mahdi", new Date());
        ReturnObject obj1 = MarketManager.addCommodity(
                4,
                "mahdi",
                4,
                100,
                new ArrayList<Category>(Arrays.asList(Category.Phone, Category.Technology)),
                8.2f,
                10);
        assertTrue(obj1.success());
        System.out.println(MarketManager.getCommoditiesList());
    }
}
