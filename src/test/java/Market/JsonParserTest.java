package Market;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonParserTest {
    @Test
    public void testEmpty() {
        JSONObject obj1 = JsonParser.parseJson("{}");
        JSONObject obj2 = JsonParser.parseJson("");
        assertEquals(obj1, obj2);
    }

    @Test
    public void testParseArray() {
        JSONObject obj = JsonParser.parseJson("{\"a\": [\"Apple\", 2, true]}");
        JSONArray arr = (JSONArray) obj.get("a");
        assertEquals("Apple", arr.get(0));
        assertEquals((long) 2, arr.get(1));
        assertEquals(true, arr.get(2));
    }

    @Test
    public void testParseEnum() {
        JSONObject obj = JsonParser.parseJson("{\"commoditiesList\":[{\"price\":100,\"name\":\"mahdi\",\"rating\":8.2,\"inStock\":10,\"id\":4,\"categories\":\"[Phone, Technology]\"}]}");
        JSONArray coms = (JSONArray) obj.get("commoditiesList");
        JSONObject com0 = (JSONObject) coms.get(0);
        assertEquals(JsonParser.parseCategory((String) com0.get("categories")).get(0), Category.Phone);
        assertEquals(JsonParser.parseCategory((String) com0.get("categories")).get(1), Category.Technology);
    }
}
