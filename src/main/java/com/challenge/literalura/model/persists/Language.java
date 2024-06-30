package com.challenge.literalura.model.persists;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @Column(name = "languages_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String type;

    @ManyToMany(mappedBy = "languages")
    private Set<Book> books = new HashSet<>();

    public Language(String type) {
        this.type = type;
    }

    public Language() {}

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
