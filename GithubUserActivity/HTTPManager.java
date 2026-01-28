package GithubUserActivity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPManager {

    static final String TOKEN = "";

    static String getJSON(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        if (TOKEN != null && !TOKEN.isEmpty()) {
            requestBuilder.header("Authorization", "token " + TOKEN);
        }

        HttpRequest request = requestBuilder.build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "HTTP ERROR: " + response.statusCode()
            );
        }

        return response.body();
    }
}
