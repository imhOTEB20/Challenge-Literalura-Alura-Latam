package com.challenge.literalura.model.consumption;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("id") Long id_on_gutendex,
        String title,
        List<AuthorData> authors,
        List<TranslatorData> translators,
        List<String> subjects,
        List<String> bookshelves,
        List<String> languages,
        Boolean copyright,
        String media_type,
        Map<String,String> formats,
        Long download_count
) {
}
