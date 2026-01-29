package ExpenseTracker;

import java.util.ArrayList;

public class Helpers {
    static String expenses_to_string(ArrayList<Expense> expenses) {
        String str = "";
        for (Expense e : expenses) {
            str += (e.toCSV() + "\n");
        }
        return str;
    }

    static Expense parse(String string) {
        String[] strs = string.split(",");
        int id = Integer.parseInt(strs[0]);
        String date = strs[1];
        String desc = strs[2];
        double amount = Double.parseDouble(strs[3]);
        String cat = strs[4];

        return new Expense(id, date, desc, amount, cat);
    }

    static ArrayList<Expense> string_to_expenses(String string) {
        String trimmed = string.trim();
        if (string.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<Expense> expenses = new ArrayList<>();
        String[] strings = trimmed.split("\n");
        for (String str : strings) {
            expenses.add(parse(str));
        }
        return expenses;
    }
}