package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Bookshelv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookshelvRepository extends JpaRepository<Bookshelv, Long> {
    Optional<Bookshelv> findByContent(String content);
}
