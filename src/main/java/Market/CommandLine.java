package Market;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarketManager market = MarketManager.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputCommand;
        do {
            try {
                System.out.println(">>");
                inputCommand = scanner.nextLine();
                String command;
                String json;
                if (inputCommand.contains(" ")) {
                    int spaceIndex = inputCommand.indexOf(" ");
                    command = inputCommand.substring(0, spaceIndex);
                    json = inputCommand.substring(spaceIndex + 1);
                } else {
                    command = inputCommand;
                    json = "{}";
                }
                JSONObject params = JsonParser.parseJson(json);
                switch (command) {
                    case "addUser": {
                        String username = (String) params.get("username");
                        String password = (String) params.get("password");
                        String email = (String) params.get("email");
                        Date birthDate = dateFormat.parse((String) params.get("birthDate"));
                        String address = (String) params.get("address");
                        int credit = (int) (long) params.get("credit");
                        market.addUser(username, password, email, birthDate, address, credit);
                        System.out.println(toResultJson(true, "Operation addUser done successfully"));
                        break;
                    }
                    case "addProvider": {
                        int id = (int) (long) params.get("id");
                        String name = (String) params.get("name");
                        Date registryDate = dateFormat.parse((String) params.get("registryDate"));
                        market.addProvider(id, name, registryDate);
                        System.out.println(toResultJson(true, "Provider added successfully"));
                        break;
                    }
                    case "addCommodity": {
                        int id = (int) (long) params.get("id");
                        String name = (String) params.get("name");
                        int providerId = (int) (long) params.get("providerId");
                        int price = (int) (long) params.get("price");
                        ArrayList<Category> categories = JsonParser.parseCategory((String) params.get("categories"));
                        float rating = (float) (double) params.get("rating");
                        int inStock = (int) (long) params.get("inStock");
                        market.addCommodity(id, name, providerId, price, categories, rating, inStock);
                        System.out.println(toResultJson(true, "Commodity added successfully"));
                        break;
                    }
                    case "getCommoditiesList": {
                        List<Commodity> commodities = market.getCommoditiesList();
                        JSONArray commoditiesJson = new JSONArray();
                        for (Commodity commodity : commodities) {
                            commoditiesJson.add(commodity.toJsonObject(true));
                        }
                        JSONObject result = new JSONObject();
                        result.put("commoditiesList", commoditiesJson);
                        System.out.printf(toResultJson(true, result.toJSONString()));
                        break;
                    }
                    case "rateCommodity":
                    case "addToBuyList":
                    case "removeFromBuyList":
                    case "getCommodityById":
                    case "getCommoditiesByCategory":
                    case "getBuyList":
                    case "exit":
                        return;
                    default:
                        throw new Exception("Invalid command: " + command);
                }
            } catch (Exception e) {
                System.out.printf(toResultJson(false, e.getMessage()));
            }
        } while (true);
    }

    public static String toResultJson(boolean success, String data) {
        return "{\"success\": %s, \"data\": \"%s\"}".formatted(success, data);
    }
}
