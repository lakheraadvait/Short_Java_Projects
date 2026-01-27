package TaskManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Task Manager CLI!");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1].trim() : "";

            switch (command) {
                case "add" -> {
                    if (argument.isEmpty()) {
                        System.out.println("Usage: add \"task title\"");
                    } else {
                        if (argument.startsWith("\"") && argument.endsWith("\"")) {
                            argument = argument.substring(1, argument.length() - 1);
                        }
                        TManager.addTask(argument);
                    }
                }
                case "list" -> {
                    if (argument.isEmpty()) {
                        TManager.listTasks();
                    } else {
                        TManager.listTasksByStatus(argument);
                    }
                }
                case "done" -> {
                    try {
                        int id = Integer.parseInt(argument);
                        TManager.markDone(id);
                        System.out.println("Marked task " + id + " as done.");
                    } catch (NumberFormatException e) {
                        System.out.println("Usage: done <task ID>");
                    }
                }
                case "progress" -> {
                    try {
                        int id = Integer.parseInt(argument);
                        TManager.markInProgress(id);
                        System.out.println("Marked task " + id + " as in-progress.");
                    } catch (NumberFormatException e) {
                        System.out.println("Usage: progress <task ID>");
                    }
                }
                case "delete" -> {
                    try {
                        int id = Integer.parseInt(argument);
                        TManager.deleteTask(id);
                        System.out.println("Deleted task " + id + ".");
                    } catch (NumberFormatException e) {
                        System.out.println("Usage: delete <task ID>");
                    }
                }
                case "help" -> {
                    System.out.println("Available commands:");
                    System.out.println("add \"task title\"   - Add a new task");
                    System.out.println("list                - List all tasks");
                    System.out.println("list <status>       - List tasks by status (todo, in-progress, done)");
                    System.out.println("done <task ID>      - Mark a task as done");
                    System.out.println("progress <task ID>  - Mark a task as in-progress");
                    System.out.println("delete <task ID>    - Delete a task");
                    System.out.println("exit                - Exit the program");
                }
                case "exit" -> {
                    System.out.println("Exiting Task Manager. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }
}
