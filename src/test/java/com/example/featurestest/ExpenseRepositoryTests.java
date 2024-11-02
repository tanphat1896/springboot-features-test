package com.example.featurestest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DatasourceConfig.class})
public class ExpenseRepositoryTests {

    @Autowired
    ExpenseRepository repository;

    @Test
    void shouldContainSingleRecord() {
        assertEquals(1, repository.findAll().size());
    }
}
