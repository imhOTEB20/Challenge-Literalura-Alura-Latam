package com.challenge.literalura.repository;

import com.challenge.literalura.model.persists.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByContent(String content);
}
