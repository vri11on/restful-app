package com.example.restfulapp.dto;

import com.example.restfulapp.entity.Author;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorDtoConverter {
    public static AuthorDto convert(Author author) {
        AuthorDto authorDto = new AuthorDto();
        BeanUtils.copyProperties(author, authorDto, "books");
        return authorDto;
    }

    public static List<AuthorDto> convert(Collection<Author> authors) {
        return authors.stream()
                .map(AuthorDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
