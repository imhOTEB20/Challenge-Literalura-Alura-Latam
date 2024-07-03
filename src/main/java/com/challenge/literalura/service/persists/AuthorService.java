package com.challenge.literalura.service.persists;

import com.challenge.literalura.model.persists.Author;
import com.challenge.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> allAuthorOnRegister() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorsAliveInYear(Integer year) {
        if (year < LocalDate.now().getYear())
            return authorRepository.findAuthorsAliveInYear(year);
        else //crear error personalizado
            return new ArrayList<>();
    }
}
