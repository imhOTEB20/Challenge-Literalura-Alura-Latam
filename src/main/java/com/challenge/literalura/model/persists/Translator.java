package com.challenge.literalura.model.persists;

import com.challenge.literalura.model.consumption.TranslatorData;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "translators")
@AttributeOverride(name = "id", column = @Column(name = "translator_id"))
public class Translator extends Person{

    @ManyToMany(mappedBy = "translators")
    private Set<Book> books = new HashSet<>();

    public Translator(String name, Integer birth_year, Integer death_year) {
        super(name, birth_year, death_year);
    }

    public Translator(TranslatorData data) {
        super(data.name(), data.birth_year(), data.death_year());
    }

    public Translator() {
        super();
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
