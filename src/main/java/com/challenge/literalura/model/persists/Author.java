package com.challenge.literalura.model.persists;

import com.challenge.literalura.model.consumption.AuthorData;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author extends Person{
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author(String name, Integer birth_year, Integer death_year) {
        super(name, birth_year, death_year);
    }

    public Author(AuthorData data) {
        super(data.name(), data.birth_year(), data.death_year());
    }

    public Author() {
        super();
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
