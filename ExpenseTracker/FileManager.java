package ExpenseTracker;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    static void initialise(String path_name) {
        Path path = Paths.get("C:\\Users\\test\\IdeaProjects\\Short_Java_Projects\\ExpenseTracker\\" + path_name);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("An unknown error occurred.");
        }
    }

    static void setCSV(String path_name, ArrayList<Expense> expenses) {
        Path path = Paths.get("C:\\Users\\test\\IdeaProjects\\Short_Java_Projects\\ExpenseTracker\\" + path_name);
        if (Files.exists(path)) {
            try {
                Files.writeString(path, Helpers.expenses_to_string(expenses));
            } catch (IOException e) {
                System.out.println("An unknown error occurred.");
            }
        }
        else {
            System.out.println("You must initialise first!");
        }
    }

    static ArrayList<Expense> getCSV(String path_name) {
        Path path = Paths.get("C:\\Users\\test\\IdeaProjects\\Short_Java_Projects\\ExpenseTracker\\" + path_name);
        if (Files.exists(path)) {
            try {
                String s = Files.readString(path);
                return Helpers.string_to_expenses(s);
            } catch (IOException e) {
                System.out.println("An unknown error occurred.");
            }
        }
        else {
            System.out.println("You must initialise first!");
        }
        return new ArrayList<>();
    }
}
