package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    List<Author> findAll();

    @Query("SELECT a FROM Author a WHERE a.birthYear IS NOT NULL AND :year >= a.birthYear AND (a.deathYear IS NULL OR :year < a.deathYear)")
    List<Author> findAuthorsAliveInYear(@Param("year") Integer year);
}
