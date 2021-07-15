package com.example.restfulapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Book extends BaseEntity {
    @Column(name = "isbn")
    private String isbn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private Author author;
}
