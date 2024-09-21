package com.example.featurestest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;

import static java.lang.Thread.sleep;
import static java.util.concurrent.CompletableFuture.runAsync;

@SpringBootApplication
public class FeaturestestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeaturestestApplication.class, args);
    }

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Bean
    ApplicationRunner runner() {
        var random = new Random();
        return a -> runAsync(() -> {
            try {
                sleep(9000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            eventPublisher.publishEvent(new Product(UUID.randomUUID().toString(), "Book", random.nextDouble(10000)));
        });
    }
}
