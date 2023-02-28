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
            try {
                if (command.equals("addUser")) {
                    JSONObject params = JsonParser.parseJson(json);
                    String username = (String) params.get("username");
                    String password = (String) params.get("password");
                    String email = (String) params.get("email");
                    String dateString = (String) params.get("birthDate");
                    Date birthDate = dateFormat.parse(dateString);
                    String address = (String) params.get("address");
                    int credit = (int)(long) params.get("credit");

                    market.addUser(username, password, email, birthDate, address, credit);
                }
                else {
                    System.out.println("{\"success\": false, \"data\": \"Invalid command\"}");
                }
            } catch (Exception e) {
                System.out.println("{\"success\": false, \"data\": " + e.getMessage() + "}");
            }

        } while (!inputCommand.equals("exit"));
    }
}
