package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE %:param%")
    List<Book> findContainsTitle(@Param("param") String title);

    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b ORDER BY b.downloadCount DESC")
    List<Book> findByDownloadCount();

    @Query("SELECT b FROM Book b JOIN b.languages l WHERE l.type = :param")
    List<Book> findByLanguage(@Param("param") String type);
}
