package com.example.restfulapp.controller;

import com.example.restfulapp.dao.BookDao;
import com.example.restfulapp.dto.BookDto;
import com.example.restfulapp.dto.BookDtoConverter;
import com.example.restfulapp.entity.Book;
import com.example.restfulapp.exceptions.IdNotPresentedException;
import com.example.restfulapp.exceptions.NotDeletedException;
import com.example.restfulapp.exceptions.UnsupportedLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @PostMapping
    public BookDto create(@RequestBody Book book) {
        return BookDtoConverter.convert(bookDao.create(book));
    }

    @PutMapping
    public BookDto update(@RequestBody Book book) {
        if (book.getId() == null) {
            throw new IdNotPresentedException(Book.class);
        }
        return BookDtoConverter.convert(bookDao.update(book));
    }

    @PatchMapping
    public BookDto patchUpdate(@RequestBody Book book) {
        if (book.getId() == null) {
            throw new IdNotPresentedException(Book.class);
        }
        return BookDtoConverter.convert(bookDao.patchUpdate(book));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Book book) {
        if (book.getId() == null) {
            throw new IdNotPresentedException(Book.class);
        }

        Boolean deleted = bookDao.delete(book);
        ResponseEntity<?> answer = new ResponseEntity<>(HttpStatus.OK);
        if (deleted) {
            return answer;
        } else {
            throw new NotDeletedException(Book.class);
        }
    }

    @GetMapping
    public Collection<BookDto> getAll(@RequestParam(defaultValue = "50") Integer limit) {
        if (limit > 1000) {
            throw new UnsupportedLimitException(limit);
        }
        Collection<Book> books = bookDao.getAll(limit);
        return BookDtoConverter.convert(books);
    }

    @ExceptionHandler({UnsupportedLimitException.class, NotDeletedException.class, IdNotPresentedException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
