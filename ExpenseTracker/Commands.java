package ExpenseTracker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Commands {

    public static void add(String description, String category, double amount) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(formatter);

        Expense e = new Expense(date, description, amount, category);
        Main.expenses.add(e);
        FileManager.setCSV("expenses.csv", Main.expenses);
    }

    public static void update(int id, String description, String category, double amount) {
        Expense e = Main.expenses.get(id - 1);
        e.description = description;
        e.amount = amount;
        e.category = category;
        FileManager.setCSV("expenses.csv", Main.expenses);
    }

    public static void delete(int id) {
        Main.expenses.remove(id - 1);
        FileManager.setCSV("expenses.csv", Main.expenses);
    }

    public static void list() {
        System.out.println(
                "ID  Date        Description        Amount   Category\n" +
                        "----------------------------------------------------"
        );
        for (Expense e : Main.expenses) {
            e.show();
        }
    }

    public static void summary() {
        double total = 0.00;
        for (Expense e : Main.expenses) {
            total += e.amount;
        }
        System.out.println("The total is " + total + ".");
    }

    public static void summaryByMonth(int month) {
        double total = 0.0;

        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        for (Expense e : Main.expenses) {
            if (e.date != null && !e.date.isEmpty()) {
                try {
                    String[] parts = e.date.split("-");
                    int expenseMonth = Integer.parseInt(parts[1]);
                    if (expenseMonth == month) {
                        total += e.amount;
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
        }

        String monthName = (month >= 1 && month <= 12) ? monthNames[month - 1] : "Unknown";

        System.out.printf("The total for %s is %.2f.%n", monthName, total);
    }

    public static void filterByCategory(String category) {
        System.out.println(
                "ID  Date        Description        Amount   Category\n" +
                        "----------------------------------------------------"
        );
        for (Expense e : Main.expenses) {
            if (e.category.equalsIgnoreCase(category)) e.show();
        }
    }

}
