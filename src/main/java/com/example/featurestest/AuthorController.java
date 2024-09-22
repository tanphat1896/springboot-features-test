package com.example.featurestest;

import com.example.codegen.tables.pojos.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping
    List<Author> list() {
        return authorRepository.list();
    }

    @PostMapping
    Author save(@RequestBody Author body) {
        return authorRepository.save(body);
    }
}
