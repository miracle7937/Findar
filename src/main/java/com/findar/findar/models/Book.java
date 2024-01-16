package com.findar.findar.models;

// src/main/java/com/yourcompany/bookstore/model/Book.java


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private boolean availability;
    public  Book(String title, String author, boolean availability){
        this.title = title;
        this.author= author;
        this.availability = availability;
    }

}
