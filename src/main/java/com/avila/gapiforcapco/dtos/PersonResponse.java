package com.avila.gapiforcapco.dtos;

import java.util.List;

public class PersonResponse {
    private String name;
    private String birth_year;
    private List<Film> films;

    private PersonResponse(String name, String birth_year, List<Film> films) {
        this.name = name;
        this.birth_year = birth_year;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public List<Film> getFilms() {
        return films;
    }

    public static PersonResponse transformIntoPersonResponse(Person person, List<Film> films) {
        return new PersonResponse(person.getName(), person.getBirth_year(), films);
    }
}
