package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Format;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormatRepository extends JpaRepository<Format, Long> {
    Optional<Format> findByUrl(String url);
}
