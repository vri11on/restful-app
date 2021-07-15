package com.example.restfulapp.dao;

import com.example.restfulapp.entity.Author;
import com.example.restfulapp.entity.Book;
import com.example.restfulapp.entity.Genre;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDaoImpl extends AbstractCrudDao<Book> implements BookDao {
    public BookDaoImpl() {
        super(Book.class);
    }

    @Transactional
    @Override
    public Book patchUpdate(Book book) {
        Book current = get(book.getId());
        if (book.getIsbn() != null) {
            current.setIsbn(book.getIsbn());
        }
        if (book.getGenre() != null) {
            current.setGenre(book.getGenre());
        }
        if (book.getAuthor() != null) {
            current.setAuthor(book.getAuthor());
        }
        return update(current);
    }

    @Transactional
    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return entityManager.createQuery("select b from Book b where b.author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Nullable
    @Transactional
    @Override
    public Book getByGenre(Genre genre) {
        try {
            return entityManager.createQuery("select b from Book b where b.genre = :genre", Book.class)
                    .setParameter("genre", genre)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
