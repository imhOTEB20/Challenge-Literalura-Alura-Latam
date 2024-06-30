package com.challenge.literalura.model.persists;

import com.challenge.literalura.model.consumption.BookData;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Map;
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

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "books_translators",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private Set<Translator> translators = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Bookshelv> bookshelves = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "books_languages",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "languages_id")
    )
    private Set<Language> languages = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Format> formats = new HashSet<>();

    private Boolean copyright;
    private String media_type;
    private Long download_count;

    public Book(String title, Boolean copyright, String media_type, Long download_count) {
        this.title = title;
        this.copyright = copyright;
        this.media_type = media_type;
        this.download_count = download_count;
    }

    public Book(BookData data) {
        this.title = data.title();
        this.copyright =  data.copyright();
        this.media_type = data.media_type();
        this.download_count = data.download_count();

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

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Long download_count) {
        this.download_count = download_count;
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


}
