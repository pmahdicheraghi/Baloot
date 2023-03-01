package Market;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MarketManagerTest {
    @Test
    public void testAddUser() {
        MarketManager mm = MarketManager.getInstance();
        try {
            boolean r1 = mm.addUser("mahdi", "x", "pm@gmail.com", new Date(), "y", 10);
            assertTrue(r1);
            boolean r2 = mm.addUser("mahdi", "y", "pmgh@gmail.com", new Date(), "x", 0);
            assertTrue(r2);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testAddUserBadSyntax() {
        MarketManager mm = MarketManager.getInstance();
        try {
            boolean r1 = mm.addUser("mah@di", "x", "pmch@gmail.com", new Date(), "x", 2);
            assertFalse(r1);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddProvider() {
        MarketManager mm = MarketManager.getInstance();
        try {
            boolean r1 = mm.addProvider(1, "mahdi", new Date());
            assertTrue(r1);
            boolean r2 = mm.addProvider(2, "hadi", new Date());
            assertTrue(r2);
            boolean r3 = mm.addProvider(1, "hassan", new Date());
            assertFalse(r3);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddCommodityWithNoProvider() {
        MarketManager mm = MarketManager.getInstance();
        try {
            mm.addProvider(3, "mahdi", new Date());
            boolean r1 = mm.addCommodity(1, "shampoo", 3, 100, new ArrayList<>(), 8.2f, 10);
            assertTrue(r1);
            boolean r2 = mm.addCommodity(1, "bag", 4, 200, new ArrayList<>(), 8.3f, 20);
            assertFalse(r2);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetCommoditiesList() {
        MarketManager mm = MarketManager.getInstance();
        try {
            mm.addProvider(4, "mahdi", new Date());
            mm.addCommodity(2, "mobile", 4, 150, new ArrayList<>(), 5.2f, 2);
            assertFalse(mm.getCommoditiesList().isEmpty());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testRateCommodity() {
        MarketManager mm = MarketManager.getInstance();
        try {
            mm.addUser("mohsen", "x", "pmch@gmail.com", new Date(), "x", 2);
            mm.addProvider(5, "mm", new Date());
            mm.addCommodity(3, "dd", 5, 120, new ArrayList<>(), 2.2f, 4);
            mm.rateCommodity("mohsen", 5, 4);
            assertEquals(4f, mm.getCommodityById(3).getRating());
            boolean r1 = mm.rateCommodity("mohsen", 3, 12);
            assertFalse(r1);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddToBuyListAndRemove() {
        MarketManager mm = MarketManager.getInstance();
        try {
            boolean r1 = mm.addToBuyList("god", 3);
            assertFalse(r1);
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            mm.addUser("hesam", "x", "pmch@gmail.com", new Date(), "x", 2);
            mm.addProvider(6, "mm", new Date());
            mm.addCommodity(4, "dd", 6, 120, new ArrayList<>(), 2.2f, 4);
            boolean r1 = mm.addToBuyList("hesam", 4);
            assertTrue(r1);
            boolean r2 = mm.removeFromBuyList("hesam", 4);
            assertTrue(r2);
            List<Commodity> cs = mm.getBuyList("hesam");
            assertEquals(0, cs.size());
        } catch (Exception e) {
            fail();
        }

        try {
            boolean r1 = mm.removeFromBuyList("hesam", 4);
            assertFalse(r1);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetCommodity() {
        MarketManager mm = MarketManager.getInstance();
        try {
            mm.addUser("gholam", "xyz", "pmch@gmail.com", new Date(), "xyz", 2);
            mm.addProvider(7, "mm", new Date());
            mm.addCommodity(5, "C5", 7, 120, new ArrayList<>(Arrays.asList(Category.Technology, Category.Vegetables)), 2.2f, 4);
            Commodity c5 = mm.getCommodityById(5);
            assertEquals("C5", c5.getName());
            List<Commodity> cs = mm.getCommoditiesByCategory(Category.Vegetables);
            assertNotEquals(0, cs.size());
        } catch (Exception e) {
            fail();
        }
    }

}
