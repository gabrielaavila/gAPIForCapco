package com.avila.gapiforcapco.dtos;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description = "Response DTO Person with name and number of films performed.")
public class PersonWithFilmsQty implements Comparable<PersonWithFilmsQty> {

    private String name;
    private Integer numberOfFilms;

    public PersonWithFilmsQty(String name, Integer numberOfFilms) {
        this.name = name;
        this.numberOfFilms = numberOfFilms;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfFilms() {
        return numberOfFilms;
    }

    public static PersonWithFilmsQty transformIntoPersonWithFilmsQty(Person person){
        return new PersonWithFilmsQty(person.getName(), person.getFilms().size());
    }

    @Override
    public int compareTo(PersonWithFilmsQty other) {
        if (this.numberOfFilms.equals(other.numberOfFilms)) {
            return this.name.compareTo(other.name);
        }
        return this.numberOfFilms - other.numberOfFilms;
    }
}
