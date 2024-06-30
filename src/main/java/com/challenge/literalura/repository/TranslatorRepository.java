package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslatorRepository extends JpaRepository<Translator, Long> {
    public Optional<Translator> findByName(String name);
}
