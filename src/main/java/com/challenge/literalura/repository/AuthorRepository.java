package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
