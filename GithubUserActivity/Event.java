package GithubUserActivity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Event {
    String type;
    String repo;
    String created_at;

    static String parseTime(String iso) {
        Instant instant = Instant.parse(iso);

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd MMM yyyy, HH:mm")
                .withZone(ZoneId.systemDefault());

        return formatter.format(instant);
    }

    void show() {
        System.out.printf("[%s] %s → %s\n", parseTime(this.created_at), this.type, this.repo);
    }

    Event(String type, String repo, String created_at) {
        this.type = type;
        this.repo = repo;
        this.created_at = created_at;
    }
}
