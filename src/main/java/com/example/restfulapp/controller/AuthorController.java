package com.example.restfulapp.controller;

import com.example.restfulapp.dao.AuthorDao;
import com.example.restfulapp.dao.BookDao;
import com.example.restfulapp.dto.AuthorDto;
import com.example.restfulapp.dto.AuthorDtoConverter;
import com.example.restfulapp.dto.BookDto;
import com.example.restfulapp.dto.BookDtoConverter;
import com.example.restfulapp.entity.Author;
import com.example.restfulapp.entity.Genre;
import com.example.restfulapp.exceptions.IdNotPresentedException;
import com.example.restfulapp.exceptions.NotDeletedException;
import com.example.restfulapp.exceptions.UnsupportedLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;


    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorDao.create(author);
    }

    @PutMapping
    public Author update(@RequestBody Author author) {
        if (author.getId() == null) {
            throw new IdNotPresentedException(Author.class);
        }
        return authorDao.update(author);
    }

    @PatchMapping
    public Author patchUpdate(@RequestBody Author author) {
        if (author.getId() == null) {
            throw new IdNotPresentedException(Author.class);
        }
        return authorDao.patchUpdate(author);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Author author) {
        if (author.getId() == null) {
            throw new IdNotPresentedException(Author.class);
        }

        Boolean deleted = authorDao.delete(author);
        ResponseEntity<?> answer = new ResponseEntity<>(HttpStatus.OK);
        if (deleted) {
            return answer;
        } else {
            throw new NotDeletedException(Author.class);
        }
    }

    @GetMapping
    public Collection<AuthorDto> getAll(@RequestParam(defaultValue = "50") Integer limit) {
        if (limit > 1000) {
            throw new UnsupportedLimitException(limit);
        }
        return AuthorDtoConverter.convert(authorDao.getAll(limit));
    }

    @GetMapping("/{id}/books")
    public Collection<BookDto> getAuthorBooks(@RequestParam(defaultValue = "50") Integer limit, @PathVariable Long id) {
        if (limit > 1000) {
            throw new UnsupportedLimitException(limit);
        }
        return BookDtoConverter.convert(bookDao.getBooksByAuthor(authorDao.get(id)));
    }

    @ExceptionHandler({UnsupportedLimitException.class, NotDeletedException.class, IdNotPresentedException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
