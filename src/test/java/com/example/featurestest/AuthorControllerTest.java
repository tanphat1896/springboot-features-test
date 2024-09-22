package com.example.featurestest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {

    @LocalServerPort
    int port;

    @Test
    void testListAuthor() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create("http://localhost:%s/authors".formatted(port)))
            .GET()
            .build();
        var res = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertTrue(res.body().contains("George"));
    }

    @Test
    void testSaveAuthor() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create("http://localhost:%s/authors".formatted(port)))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("""
                {
                    "firstName": "Test",
                    "lastName": "Test"
                }"""))
            .build();
        var res = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(res.body().contains("Test"));
    }
}
