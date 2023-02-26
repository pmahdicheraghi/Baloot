package Market;

import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputCommand;
        do {
            System.out.println(">>");
            inputCommand = scanner.nextLine();
        } while (!inputCommand.equals("exit"));
    }
}
