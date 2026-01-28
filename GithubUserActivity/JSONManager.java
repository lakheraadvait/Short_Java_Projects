package GithubUserActivity;

import java.util.ArrayList;

public class JSONManager {

    static ArrayList<String> parse(String string) {
        if (string.isEmpty()) return new ArrayList<String>();
        string = string.substring(1, string.length() - 1);
        ArrayList<String> strings = new ArrayList<>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '{') {
                depth += 1;
                if (depth == 1) {
                    start = i;
                }
            } else if (string.charAt(i) == '}') {
                depth -= 1;
                if (depth == 0) {
                    String str = string.substring(start, i);
                    strings.add(str + "}");
                }
            }
        }
        return strings;
    }

    static Event event_helper(String string) {
        string = string.substring(1, string.length() - 1).trim();
        String[] parts = string.split(",");

        String type = "";
        String repo = "";
        String created_at = "";

        for (String s : parts) {
            s = s.trim();
            //System.out.println(s);

            if (s.startsWith("\"type\"")) {
                if (s.endsWith("Event\"")) {
                    type = s.split(":")[1].trim().replace("\"", "");
                }
            } else if (s.startsWith("\"name\"") && repo.isEmpty()) {
                repo = s.split(":")[1].trim().replace("\"", "");
            } else if (s.startsWith("\"created_at\"")) {
                created_at = s.substring(
                        s.indexOf("\"", s.indexOf(":")) + 1,
                        s.lastIndexOf("\"")
                );
            }
        }

        return new Event(type, repo, created_at);
    }

    static ArrayList<Event> eventsList(String s) {
        ArrayList<String> strings = parse(s);
        ArrayList<Event> events = new ArrayList<>();
        for (String i : strings) {
            events.add(event_helper(i));
        }
        return events;
    }
}