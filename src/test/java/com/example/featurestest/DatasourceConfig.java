package com.example.featurestest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration
public class DatasourceConfig {

    @Bean
    @ServiceConnection
    MySQLContainer<?> mysqlContainer() {
        return new MySQLContainer<>("mysql:8.0.32")
            .withUsername("foo")
            .withPassword("bar")
            .withDatabaseName("expense")
            .withInitScript("init-mysql.sql");
    }
}
