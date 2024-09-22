package com.example.featurestest;

import com.example.codegen.tables.pojos.Author;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.codegen.Tables.AUTHOR;

public interface AuthorRepository {

    List<Author> list();

    Author save(Author author);

    @Repository
    @RequiredArgsConstructor
    class Impl implements AuthorRepository {
        private final DSLContext ctx;

        @Override
        public List<Author> list() {
            return ctx.selectFrom(AUTHOR).fetchInto(Author.class);
        }

        @Override
        public Author save(Author author) {
            var ret = ctx.insertInto(AUTHOR, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .values(author.getFirstName(), author.getLastName())
                .returning()
                .fetch()
                .into(Author.class);
            return ret.getFirst();
        }
    }
}
