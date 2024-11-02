package com.example.featurestest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl {

    private final ExpenseRepository expenseRepository;

    @Transactional
    public void updateCat(long id, String cat) {
        expenseRepository.findById(id).ifPresent(e -> e.setCategory(cat));
    }
}
