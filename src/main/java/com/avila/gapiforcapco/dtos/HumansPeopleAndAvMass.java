package com.avila.gapiforcapco.dtos;

import java.util.List;

public class HumansPeopleAndAvMass {
    private List<PersonAndMass> people;
    private String averageMass;

    public HumansPeopleAndAvMass(List<PersonAndMass> people, String averageMass) {
        this.people = people;
        this.averageMass = averageMass;
    }

    public List<PersonAndMass> getPeople() {
        return people;
    }

    public String getAverageMass() {
        return averageMass;
    }
}
