package com.challenge.literalura.service.persists;

import com.challenge.literalura.model.persists.*;
import com.challenge.literalura.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TranslatorRepository translatorRepository;

    @Autowired
    private BookshelvRepository bookshelvRepository;

    @Autowired
    private FormatRepository formatRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Transactional
    public void saveBook(Book book) {
        Optional<Book> bookOnDB = bookRepository.findByTitle(book.getTitle());
        if (bookOnDB.isEmpty()) {
            Set<Author> newAuthors = new HashSet<>(book.getAuthors());
            Set<Translator> newTranslators = new HashSet<>(book.getTranslators());
            Set<Language> newLanguages = new HashSet<>(book.getLanguages());
            Set<Subject> newSubjects = new HashSet<>(book.getSubjects());
            Set<Bookshelv> newBookshelves = new HashSet<>(book.getBookshelves());
            Set<Format> newFormats = new HashSet<>(book.getFormats());

            book.setAuthors(checkAuthors(newAuthors, book));
            book.setLanguages(checkLanguages(newLanguages, book));
            book.setSubjects(checkSubject(newSubjects));
            book.setFormats(checkFormat(newFormats));
            book.setTranslators(checkTranslators(newTranslators, book));
            book.setBookshelves(checkBookShelves(newBookshelves));
            bookRepository.save(book);
        }
    }

    private Set<Author> checkAuthors(Set<Author> authors, Book book) {
        return authors.stream()
                .map(author -> {
                    Optional<Author> authorOnDB = authorRepository.findByName(author.getName());
                    if(authorOnDB.isPresent()) {
                        Author currentAuthor = authorOnDB.get();
                        currentAuthor.setBirth_year(author.getBirth_year());
                        currentAuthor.setDeath_year(author.getDeath_year());
                        currentAuthor.getBooks().add(book);
                        return authorRepository.save(currentAuthor);
                    }
                    else return authorRepository.save(author);
                })
                .collect(Collectors.toSet());
    }

    private Set<Language> checkLanguages(Set<Language> languages, Book book) {
        return languages.stream()
                .map(language -> {
                    Optional<Language> languageOnDB = languageRepository.findByType(language.getType());
                    if(languageOnDB.isPresent()) {
                        Language currentLanguage = languageOnDB.get();
                        currentLanguage.getBooks().add(book);
                        return languageRepository.save(currentLanguage);
                    }
                    else {

                        return languageRepository.save(language);}
                })
                .collect(Collectors.toSet());
    }

    private Set<Translator> checkTranslators(Set<Translator> translators, Book book) {
        return translators.stream()
                .map(translator -> {
                    Optional<Translator> translatorOnDB = translatorRepository.findByName(translator.getName());
                    if(translatorOnDB.isPresent()) {
                        Translator currentTranslator = translatorOnDB.get();
                        currentTranslator.setBirth_year(translator.getBirth_year());
                        currentTranslator.setDeath_year(translator.getDeath_year());
                        currentTranslator.getBooks().add(book);
                        return translatorRepository.save(currentTranslator);
                    }
                    return translatorRepository.save(translator);
                })
                .collect(Collectors.toSet());
    }

    private Set<Bookshelv> checkBookShelves(Set<Bookshelv> bookshelves) {
        return bookshelves.stream()
                .map(bookshelv -> {
                    Optional<Bookshelv> bookShelvOnDB = bookshelvRepository.findByContent(bookshelv.getContent());
                    if(bookShelvOnDB.isPresent()) {
                        Bookshelv currentBookShelv = bookShelvOnDB.get();
                        currentBookShelv.setContent(bookshelv.getContent());
                        return bookshelvRepository.save(currentBookShelv);
                    }
                    return bookshelvRepository.save(bookshelv);
                })
                .collect(Collectors.toSet());
    }

    private Set<Format> checkFormat(Set<Format> formats) {
        return formats.stream()
                .map(format -> {
                    Optional<Format> formatOnDB = formatRepository.findByUrl(format.getUrl());
                    if (formatOnDB.isPresent()) {
                        Format currentFormat = formatOnDB.get();
                        currentFormat.setUrl(format.getUrl());
                        currentFormat.setCode(format.getCode());
                        return formatRepository.save(currentFormat);
                    }
                    return formatRepository.save(format);
                })
                .collect(Collectors.toSet());
    }

    private Set<Subject> checkSubject(Set<Subject> subjects) {
        return subjects.stream()
                .map(subject -> {
                    Optional<Subject> subjectOnDB = subjectRepository.findByContent(subject.getContent());
                    if (subjectOnDB.isPresent()) {
                        Subject currentSubject = subjectOnDB.get();
                        currentSubject.setContent(subject.getContent());
                        return subjectRepository.save(currentSubject);
                    }
                    return subjectRepository.save(subject);
                })
                .collect(Collectors.toSet());
    }
    /*
    public Optional<Book> findBookByTitleOnWeb(String title) {
    }

    public Optional<Book> findBookByTitleOnRegister(String title) {
    }

    public List<Book> findTop5ByDownloadCount() {
    }
    */
}
