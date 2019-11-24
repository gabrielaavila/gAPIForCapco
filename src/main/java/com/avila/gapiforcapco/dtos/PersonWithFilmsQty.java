package com.avila.gapiforcapco.dtos;

import java.util.List;

public class PersonWithFilmsQty {

    private String name;
    private List<Film> films;

    public PersonWithFilmsQty(String name, List<Film> films) {
        this.name = name;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public List<Film> getFilms() {
        return films;
    }
}
