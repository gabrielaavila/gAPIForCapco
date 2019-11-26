package com.avila.gapiforcapco.dtos;

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

    public String getMass() {
        return mass;
    }

    public static PersonAndMass transformIntoPersonAndMass(Person person) {
        return new PersonAndMass(person.getName(), person.getMass());
    }
}
