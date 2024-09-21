package com.example.featurestest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LongPollingTest {

    @LocalServerPort
    int port;

    @Test
    void longPolling() {
        var client = HttpClient.newHttpClient();
        var asyncRes = client.sendAsync(
            HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:%d/notification/product".formatted(port)))
                .timeout(Duration.ofSeconds(10))
                .build(),
            HttpResponse.BodyHandlers.ofString()
        );
        var res = asyncRes.join();
        assertEquals(res.body().length(), 36);
    }

    @Test
    void longPollingError() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:%d/notification/product".formatted(port)))
            .timeout(Duration.ofSeconds(10))
            .build();
        IntStream.rangeClosed(0, 100).forEach(i -> client.sendAsync(req, HttpResponse.BodyHandlers.ofString()));

        var result = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(result.body());
    }
}
