package TaskManager;

import java.util.ArrayList;

public class TManager {

    public static void addTask(String description) {

        FManager.initialise("tasks");
        ArrayList<Task> tasks = FManager.get("tasks");

        for (Task t : tasks) {
            if (t.description.equalsIgnoreCase(description)) {
                System.out.println("That task already exists!");
                return;
            }
        }

        Task task = new Task(description);
        tasks.add(task);

        FManager.put("tasks", tasks);
        System.out.println("Task added successfully.");
    }

    public static void listTasks() {
        FManager.initialise("tasks");
        ArrayList<Task> tasks = FManager.get("tasks");

        if (!tasks.isEmpty()) {
            System.out.println("ID | Status | Title");
            System.out.println("--------------------------");
            for (Task task : tasks) {
                task.show();
            }
        } else {
            System.out.println("No tasks created as of now.");
        }
    }

    public static void listTasksByStatus(String status) {
        FManager.initialise("tasks");
        ArrayList<Task> tasks = FManager.get("tasks");
        if (!tasks.isEmpty() && (status.equals("todo") || status.equals("in-progress") || status.equals( "done"))) {
            System.out.print("ID | Status | Title\n");
            System.out.println("--------------------------");
            for (Task task : tasks) {
                if (task.status.equals(status)) {
                    task.show();
                }
            }
        } else {
            if (tasks.isEmpty()) {
                System.out.println("No tasks created as of now .");
            }

            else {
                System.out.println("Status must be either todo, in-progress or done");
            }
        }
    }

    public static void markDone(int id) {
        FManager.initialise("tasks");
        ArrayList<Task> tasks = FManager.get("tasks");
        for (Task task : tasks) {
            if (task.id == id) {
                task.status = "done";
                task.updateTimestamp();
            }
        }
        FManager.put("tasks", tasks);
    }

    public static void markInProgress(int id) {
        FManager.initialise("tasks");
        ArrayList<Task> tasks = FManager.get("tasks");
        for (Task task : tasks) {
            if (task.id == id) {
                task.status = "in-progress";
                task.updateTimestamp();
            }
        }
        FManager.put("tasks", tasks);
    }

    public static void deleteTask(int id) {
        FManager.initialise("tasks");
        ArrayList<Task> tasks = FManager.get("tasks");
        tasks.remove(id - 1);
        FManager.put("tasks", tasks);
    }
}
