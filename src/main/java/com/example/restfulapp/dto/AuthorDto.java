package com.example.restfulapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorDto {
    private String firstName;

    private String lastName;

    private String middleName;

    private LocalDate birthDate;
}
