package com.example.restfulapp.dao;

import com.example.restfulapp.entity.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Repository
public class AuthorDaoImpl extends AbstractCrudDao<Author> implements AuthorDao {
    public AuthorDaoImpl() {
        super(Author.class);
    }

    @Transactional
    @Override
    public Author patchUpdate(Author author) {
        Author current = get(author.getId());
        if (StringUtils.hasText(author.getFirstName())) {
            current.setFirstName(author.getFirstName());
        }
        if (StringUtils.hasText(author.getLastName())) {
            current.setFirstName(author.getLastName());
        }
        if (StringUtils.hasText(author.getMiddleName())) {
            current.setFirstName(author.getMiddleName());
        }
        if (author.getBirthDate() != null) {
            current.setBirthDate(author.getBirthDate());
        }
        if (author.getBooks() != null) {
            current.setBooks(author.getBooks());
        }
        return null;
    }
}
