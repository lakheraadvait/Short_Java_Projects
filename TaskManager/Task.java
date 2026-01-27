package TaskManager;

import java.time.LocalDateTime;

public class Task {

    static int counter = 0;

    int id;
    String createdAt;
    String updatedAt;
    String status;
    String description;

    Task(String description) {
        this.description = description;


        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = this.createdAt;


        counter++;
        this.id = counter;


        this.status = "todo";
    }

    Task() {

    }

    void updateTimestamp() {
        this.updatedAt = LocalDateTime.now().toString();
    }

    void show() {
        System.out.println(
                this.id + " | " +
                        this.status + " | " +
                        this.description
        );
    }
}
