package TaskManager;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;

public class FManager {

    static String task_to_string(Task task) {
        String s = "{\n";
        s += ("\"id\": " + task.id);
        s += (",\n\"description\": \"" + task.description + "\"");
        s += (",\n\"status\": \"" + task.status + "\"");
        s += (",\n\"createdAt\": \"" + task.createdAt + "\"");
        s += (",\n\"updatedAt\": \"" + task.updatedAt + "\"\n");
        s += "}";
        return s;
    }

    static String getValue(String json, String key) {
        String pattern = "\"" + key + "\":";
        int keyIndex = json.indexOf(pattern);

        if (keyIndex == -1) {
            return "";
        }

        int start = keyIndex + pattern.length();

        if (start >= json.length()) {
            return "";
        }

        while (start < json.length() && (json.charAt(start) == ' ' || json.charAt(start) == '\n')) {
            start++;
        }

        if (json.charAt(start) == '"') {
            start++;
            int end = json.indexOf("\"", start);

            if (end == -1) {
                return "";
            }

            return json.substring(start, end);
        }

        int end = json.indexOf(",", start);
        if (end == -1) {
            end = json.indexOf("}", start);
        }

        if (end == -1) {
            return "";
        }

        return json.substring(start, end).trim();
    }

    static ArrayList<Task> string_to_task(String string) {
        ArrayList<Task> list = new ArrayList<>();

        if (string.length() <= 2) {
            return list;
        }

        String json = string.substring(1, string.length() - 1);

        String[] taskStrings = json.split("\\},\\s*\\{");

        for (int i = 0; i < taskStrings.length; i++) {
            if (!taskStrings[i].startsWith("{")) {
                taskStrings[i] = "{" + taskStrings[i];
            }
            if (!taskStrings[i].endsWith("}")) {
                taskStrings[i] = taskStrings[i] + "}";
            }
        }

        for (String taskJson : taskStrings) {
            Task t = new Task();

            String idValue = getValue(taskJson, "id");

            if (idValue.isEmpty()) {
                continue;
            }

            t.id = Integer.parseInt(idValue);
            t.description = getValue(taskJson, "description");
            t.status = getValue(taskJson, "status");
            t.createdAt = getValue(taskJson, "createdAt");
            t.updatedAt = getValue(taskJson, "updatedAt");

            list.add(t);
        }

        return list;
    }

    static void initialise(String path_name) {
        Path p = Paths.get(
                "C:\\Users\\test\\IdeaProjects\\Short_Java_Projects\\TaskManager\\" + path_name + ".json"
        );

        try {
            if (!Files.exists(p)) {
                Files.createFile(p);
                Files.writeString(p, "[]");
                System.out.println("Created file successfully.");
            } else {
                String s = Files.readString(p);
                ArrayList<Task> list = string_to_task(s);
                Task.counter = list.size() + 1;
            }
        } catch (IOException e) {
            System.out.println("Could not initialise file.");
        }
    }

    static void put(String path_name, ArrayList<Task> tasks) {
        Path p = Paths.get(
                "C:\\Users\\test\\IdeaProjects\\Short_Java_Projects\\TaskManager\\" + path_name + ".json"
        );

        String s = "[";

        try {
            if (!Files.exists(p)) {
                System.out.println("No file exists. You must initialise the file first.");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    s += task_to_string(tasks.get(i));

                    if (i < tasks.size() - 1) {
                        s += ",\n";
                    } else {
                        s += "\n";
                    }
                }

                s += "]";
                Files.writeString(p, s);
                System.out.println("Successfully wrote the tasks to json file.");
            }
        } catch (IOException e) {
            System.out.println("Could not write file.");
        }
    }

    static ArrayList<Task> get(String path_name) {
        Path p = Paths.get(
                "C:\\Users\\test\\IdeaProjects\\Short_Java_Projects\\TaskManager\\" + path_name + ".json"
        );

        try {
            if (!Files.exists(p)) {
                System.out.println("No file exists. You must initialise the file first.");
                return new ArrayList<>();
            }

            String s = Files.readString(p);

            if (s.length() > 2) {
                return string_to_task(s);
            }

            return new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Could not read file.");
            return new ArrayList<>();
        }
    }
}