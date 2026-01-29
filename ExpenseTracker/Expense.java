package ExpenseTracker;

public class Expense {
    static int counter = 0;
    int id;
    String date;
    String description;
    double amount;
    String category;

    Expense(String date, String description, double amount, String category) {
        counter ++;
        this.id = counter;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
    Expense(int id, String date, String description, double amount, String category) {
        counter = id;
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    void show() {
        System.out.printf(
                "%-3d %-10s %-20s $%-7.2f %-10s%n",
                id, date, description, amount, category
        );
    }

    String toCSV() {
        return this.id + "," + this.date + "," + this.description + "," + this.amount + "," + this.category;
    }
}
