package com.example.restfulapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Genre extends BaseEntity {
    @Column(name = "description", length = 1000, nullable = false)
    private String description;
}
