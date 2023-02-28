package Market;

import org.json.simple.JSONObject;

import java.util.Date;
import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarketManager market = MarketManager.getInstance();
        String inputCommand;
        do {
            System.out.println(">>");
            inputCommand = scanner.nextLine();
            String command = "addUser";
            String json = " {\"username\": \"us%er1\", \"password\": \"1234\", \"email\": \"user@gmail.com\", \"birthDate\":\n" +
                    "\"1977-09-15\", \"address\": \"address1\", \"credit\": 1500}";

            try {
                if (command == "addUser") {
                    JSONObject params = JsonParser.parseJson(json);
                    String username = (String) params.get("username");
                    String password = (String) params.get("password");
                    String email = (String) params.get("email");
                    Date birthDate = new Date();
                    String address = (String) params.get("address");
                    int credit = (int)(long) params.get("credit");

                    market.addUser(username, password, email, birthDate, address, credit);
                } else {
                    System.out.println("{\"success\": false, \"data\": \"Invalid command\"}");
                }
            } catch (Exception e) {
                System.out.println("{\"success\": false, \"data\": " + e.getMessage() + "}");
            }

        } while (!inputCommand.equals("exit"));
    }
}
