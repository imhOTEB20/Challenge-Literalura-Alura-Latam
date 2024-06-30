package com.challenge.literalura.model.persists;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer birth_year;
    private Integer death_year;

    public Person(String name, Integer birth_year, Integer death_year) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }
    public Person() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }
}
