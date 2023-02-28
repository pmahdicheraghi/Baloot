package Market;

import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarketManager market = MarketManager.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputCommand;
        do {
            System.out.println(">>");
            inputCommand = scanner.nextLine();
            int spaceIndex = inputCommand.indexOf(" ");
            String command = inputCommand.substring(0, spaceIndex);
            String json = inputCommand.substring(spaceIndex + 1);
            JSONObject params = JsonParser.parseJson(json);
            String dateString;
            try {
                switch (command) {
                    case "addUser":
                        String username = (String) params.get("username");
                        String password = (String) params.get("password");
                        String email = (String) params.get("email");
                        dateString = (String) params.get("birthDate");
                        Date birthDate = dateFormat.parse(dateString);
                        String address = (String) params.get("address");
                        int credit = (int) (long) params.get("credit");
                        market.addUser(username, password, email, birthDate, address, credit);
                        printSuccess("addUser");
                        break;
                    case "addProvider":
                        int id = (int)(long) params.get("id");
                        String name = (String) params.get("name");
                        dateString=(String) params.get("registryDate");
                        Date registryDate = dateFormat.parse(dateString);
                        market.addProvider(id,name,registryDate);
                        printSuccess("addProvider");
                    case "addCommodity":
                    case "getCommoditiesList":
                    case "rateCommodity":
                    case "addToBuyList":
                    case "removeFromBuyList":
                    case "getCommodityById":
                    case "getCommoditiesByCategory":
                    case "getBuyList":
                    default:
                        System.out.println("{\"success\": false, \"data\": \"Invalid command\"}");

                }
            } catch (Exception e) {
                System.out.println("{\"success\": false, \"data\": " + e.getMessage() + "}");
            }

        } while (!inputCommand.equals("exit"));
    }
    public static void printSuccess(String OperationName){
        String message="Operation "+ OperationName + "done successfully";
        System.out.printf("{\"success\": true, \"data\": \"%s\"}",message);
    }
}
