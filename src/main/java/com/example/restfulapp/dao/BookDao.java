package com.example.restfulapp.dao;

import com.example.restfulapp.entity.Author;
import com.example.restfulapp.entity.Book;
import com.example.restfulapp.entity.Genre;

import java.util.List;

public interface BookDao extends CrudDao<Book> {
    List<Book> getBooksByAuthor(Author author);
    Book getByGenre(Genre genre);
}
