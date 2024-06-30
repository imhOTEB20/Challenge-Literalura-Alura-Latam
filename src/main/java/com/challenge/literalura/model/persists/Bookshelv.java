package com.challenge.literalura.model.persists;

import jakarta.persistence.*;

@Entity
@Table(name = "bookshelves")
public class Bookshelv {
    @Id
    @Column(name = "bookshelv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    private String content;

    public Bookshelv(String content, Book book) {
        this.content = content;
        this.book = book;
    }

    public Bookshelv() {}

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
