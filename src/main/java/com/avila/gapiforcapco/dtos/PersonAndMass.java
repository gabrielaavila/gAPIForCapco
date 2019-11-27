package com.avila.gapiforcapco.dtos;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Response DTO Person with name and mass information.")
public class PersonAndMass {
    private String name;
    private String mass;

    private PersonAndMass(String name, String mass) {
        this.name = name;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public static PersonAndMass transformIntoPersonAndMass(Person person) {
        return new PersonAndMass(person.getName(), person.getMass());
    }
}
