package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Long> {
    Optional<Language> findByType(String type);
}
