package com.challenge.literalura.model.persists;

import com.challenge.literalura.model.consumption.AuthorData;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author extends Person{
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    public Author(String name, Integer birthYear, Integer deathYear) {
        super(name, birthYear, deathYear);
    }

    public Author(AuthorData data) {
        super(data.name(), data.birthYear(), data.deathYear());
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

    @Override
    public String toString() {
        return """
                ----- AUTOR ------
                Nombre: %s
                Año de Nacimiento = %d
                Año de Fallecimiento = %d
                Libros: %s
                ------------------
                """
                .formatted(
                        this.getName(),
                        this.getBirthYear(),
                        this.getDeathYear(),
                        this.books.stream()
                                .map(Book::getTitle)
                                .collect(Collectors.joining("; "))
                );
    }
}
