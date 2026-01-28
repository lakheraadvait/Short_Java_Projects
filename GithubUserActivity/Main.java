package GithubUserActivity;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void showDetails(String user) {
        String url = ("https://api.github.com/users/"+ user +"/events");
        String json = HTTPManager.getJSON(url);
        if (json.isEmpty() || json.equalsIgnoreCase("[]")) {
            System.out.println("No such account.");
        }
        else {
            ArrayList<Event> list = JSONManager.eventsList(json);
            for (Event e : list) {
                e.show();
            }
        }
    }

    static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to github detail fetcher CLI.");
        System.out.println("Enter \"github-activity\" followed by the username to fetch details.");
        System.out.println("Enter \"exit\" to exit the program.\n");

        String input = "";
        while (!input.equalsIgnoreCase("exit")) {
            System.out.print("> ");
            input = s.nextLine();
            if (input.startsWith("github-activity " ) && input.trim().length() != 15) {
                String user = input.substring(16, input.length());
                try {
                    showDetails(user);
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred.");
                }
            }
            else {
                if (!input.equalsIgnoreCase("exit") && !input.isEmpty()) {
                    System.out.println("The command is \"github-activity user\" or \"exit\"!");
                }
            }
        }
        System.out.println("Have a nice day!");
        s.close();
    }
}
