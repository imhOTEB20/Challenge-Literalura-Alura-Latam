package com.challenge.literalura.model.persists;

import jakarta.persistence.*;

@Entity
@Table(name = "formats")
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "format_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    private String code;
    private String url;

    public Format(String code, String url, Book book) {
        this.code = code;
        this.url = url;
        this.book = book;
    }
    public Format() {}

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
