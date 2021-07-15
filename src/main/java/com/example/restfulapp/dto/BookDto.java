package com.example.restfulapp.dto;

import com.example.restfulapp.entity.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private String isbn;

    private Genre genre;

    private AuthorDto author;
}
