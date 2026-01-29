package ExpenseTracker;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        FileManager.initialise("expenses.csv");
        expenses = FileManager.getCSV("expenses.csv");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Expense Tracker CLI!");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            String[] tokens = input.split("\\s+");
            if (tokens.length == 0) continue;

            String command = tokens[0].toLowerCase();

            try {
                switch (command) {
                    case "help":
                        System.out.println("""
                                Available commands:
                                add - Add a new expense
                                list - List all expenses
                                summary - Show total expenses
                                summarymonth - Show total expenses for a month
                                delete - Delete an expense by ID
                                update - Update an expense by ID
                                filter - Show expenses by category
                                exit / quit - Exit the program
                                """);
                        break;

                    case "add":
                        System.out.print("Description: ");
                        String description = scanner.nextLine();
                        System.out.print("Category [General]: ");
                        String category = scanner.nextLine();
                        if (category.isEmpty()) category = "General";
                        System.out.print("Amount: ");
                        double amount = Double.parseDouble(scanner.nextLine());

                        Commands.add(description, category, amount);
                        System.out.println("Expense added successfully (ID: " + (expenses.size()) + ")");
                        break;

                    case "list":
                        Commands.list();
                        break;

                    case "summary":
                        Commands.summary();
                        break;

                    case "summarymonth":
                        System.out.print("Month number (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine());
                        Commands.summaryByMonth(month);
                        break;

                    case "delete":
                        System.out.print("ID to delete: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        if (deleteId <= 0 || deleteId > expenses.size()) {
                            System.out.println("Error: Invalid ID.");
                        } else {
                            Commands.delete(deleteId);
                            System.out.println("Expense deleted successfully");
                        }
                        break;

                    case "update":
                        System.out.print("ID to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        if (updateId <= 0 || updateId > expenses.size()) {
                            System.out.println("Error: Invalid ID.");
                            break;
                        }
                        Expense e = expenses.get(updateId - 1);

                        System.out.print("New description [" + e.description + "]: ");
                        String newDesc = scanner.nextLine();
                        if (newDesc.isEmpty()) newDesc = e.description;

                        System.out.print("New category [" + e.category + "]: ");
                        String newCat = scanner.nextLine();
                        if (newCat.isEmpty()) newCat = e.category;

                        System.out.print("New amount [" + e.amount + "]: ");
                        String amountStr = scanner.nextLine();
                        double newAmt = amountStr.isEmpty() ? e.amount : Double.parseDouble(amountStr);

                        Commands.update(updateId, newDesc, newCat, newAmt);
                        System.out.println("Expense updated successfully");
                        break;

                    case "filter":
                        System.out.print("Category to filter: ");
                        String filterCat = scanner.nextLine();
                        Commands.filterByCategory(filterCat);
                        break;

                    default:
                        System.out.println("Unknown command. Type 'help' for commands.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        scanner.close();
    }
}
