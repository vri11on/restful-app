package com.example.restfulapp.dto;

import com.example.restfulapp.entity.Book;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookDtoConverter {
    public static BookDto convert(Book book) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto, "author");
        bookDto.setAuthor(AuthorDtoConverter.convert(book.getAuthor()));
        return bookDto;
    }

    public static List<BookDto> convert(Collection<Book> books) {
        return books.stream()
                .map(BookDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
