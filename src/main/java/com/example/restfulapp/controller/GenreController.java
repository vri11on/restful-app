package com.example.restfulapp.controller;

import com.example.restfulapp.dao.BookDao;
import com.example.restfulapp.dao.GenreDao;
import com.example.restfulapp.entity.Genre;
import com.example.restfulapp.exceptions.HasConstraintsException;
import com.example.restfulapp.exceptions.IdNotPresentedException;
import com.example.restfulapp.exceptions.NotDeletedException;
import com.example.restfulapp.exceptions.UnsupportedLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private BookDao bookDao;


    @PostMapping
    public Genre create(@RequestBody Genre genre) {
        return genreDao.create(genre);
    }

    @PutMapping
    public Genre update(@RequestBody Genre genre) {
        if (genre.getId() == null) {
            throw new IdNotPresentedException(Genre.class);
        }
        return genreDao.update(genre);
    }

    @PatchMapping
    public Genre patchUpdate(@RequestBody Genre genre) {
        if (genre.getId() == null) {
            throw new IdNotPresentedException(Genre.class);
        }
        return genreDao.patchUpdate(genre);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Genre genre) {
        if (genre.getId() == null) {
            throw new IdNotPresentedException(Genre.class);
        }
        if (!isDeletable(genre)) {
            throw new HasConstraintsException(genre.getId());
        }

        Boolean deleted = genreDao.delete(genre);
        ResponseEntity<?> answer = new ResponseEntity<>(HttpStatus.OK);
        if (deleted) {
            return answer;
        } else {
            throw new NotDeletedException(Genre.class);
        }
    }

    @GetMapping
    public Collection<Genre> getAll(@RequestParam(defaultValue = "50") Integer limit) {
        if (limit > 1000) {
            throw new UnsupportedLimitException(limit);
        }
        return genreDao.getAll(limit);
    }

    private boolean isDeletable(Genre genre) {
        return bookDao.getByGenre(genre) == null;
    }

    @ExceptionHandler({UnsupportedLimitException.class,
            NotDeletedException.class,
            IdNotPresentedException.class,
            HasConstraintsException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
