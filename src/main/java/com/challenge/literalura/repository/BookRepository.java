package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
