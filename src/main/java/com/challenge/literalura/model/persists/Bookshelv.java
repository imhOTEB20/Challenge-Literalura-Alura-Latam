package com.challenge.literalura.model.persists;

import jakarta.persistence.*;

@Entity
@Table(name = "bookshelves")
public class Bookshelv {
    @Id
    @Column(name = "bookshelv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String content;

    public Bookshelv(String content) {
        this.content = content;
    }

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
