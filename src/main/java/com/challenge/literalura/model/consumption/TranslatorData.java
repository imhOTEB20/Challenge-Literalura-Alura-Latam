package com.challenge.literalura.model.consumption;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TranslatorData(
        String name,
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear
) {
}
