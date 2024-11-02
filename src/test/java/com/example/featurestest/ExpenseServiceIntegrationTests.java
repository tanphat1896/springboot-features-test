package com.example.featurestest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DatasourceConfig.class})
public class ExpenseServiceIntegrationTests {

    @Autowired
    ExpenseServiceImpl expenseService;

    @Autowired
    ExpenseRepository expenseRepository;

    @Test
    void shouldContainSingleRecord() {
        expenseService.updateCat(1L, "TestOnly");
        assertEquals("TestOnly", expenseRepository.findById(1L).get().getCategory());
    }
}
