package com.challenge.literalura.model.persists;

import com.challenge.literalura.model.consumption.BookData;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
    fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_translators",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private Set<Translator> translators = new HashSet<>();

    @OneToMany(mappedBy = "book",
            fetch = FetchType.EAGER)
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "book",
            fetch = FetchType.EAGER)
    private Set<Bookshelv> bookshelves = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_languages",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "languages_id")
    )
    private Set<Language> languages = new HashSet<>();

    @OneToMany(mappedBy = "book",
            fetch = FetchType.EAGER)
    private Set<Format> formats = new HashSet<>();

    private Boolean copyright;
    private String mediaType;
    private Long downloadCount;

    public Book(String title, Boolean copyright, String mediaType, Long downloadCount) {
        this.title = title;
        this.copyright = copyright;
        this.mediaType = mediaType;
        this.downloadCount = downloadCount;
    }

    public Book(BookData data) {
        this.title = data.title();
        this.copyright =  data.copyright();
        this.mediaType = data.mediaType();
        this.downloadCount = data.downloadCount();

        this.authors = data.authors().stream()
                .map(Author::new)
                .collect(Collectors.toSet());

        this.translators =  data.translators().stream()
                .map(Translator::new)
                .collect(Collectors.toSet());

        this.subjects =  data.subjects().stream()
                .map(content -> {
                    return new Subject(content, this);
                })
                .collect(Collectors.toSet());

        this.bookshelves = data.bookshelves().stream()
                .map(content -> {
                    return new Bookshelv(content, this);
                })
                .collect(Collectors.toSet());

        this.languages = data.languages().stream()
                .map(Language::new)
                .collect(Collectors.toSet());

        this.formats = data.formats().keySet().stream()
                .map(code -> {
                    return new Format(code, data.formats().get(code), this);
                })
                .collect(Collectors.toSet());
    }

    public Book(){}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(Set<Translator> translators) {
        this.translators = translators;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Bookshelv> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<Bookshelv> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<Format> getFormats() {
        return formats;
    }

    public void setFormats(Set<Format> formats) {
        this.formats = formats;
    }

    @Override
    public String toString() {
        return """
                ----- LIBRO -----
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %d
                ----------------"""
                .formatted(
                        this.getTitle(),
                        this.authors.stream()
                                .map(Author::getName)
                                .collect(Collectors.joining("; ")),
                        this.languages.stream()
                                .map(Language::getType)
                                .collect(Collectors.joining("; ")),
                        this.getDownloadCount()
                );
    }
}
