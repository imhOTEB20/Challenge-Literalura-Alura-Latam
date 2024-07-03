package com.challenge.literalura.model.persists;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer birthYear;
    private Integer deathYear;

    public Person(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
    public Person() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }
}
