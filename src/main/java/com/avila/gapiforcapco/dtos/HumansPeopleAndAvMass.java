package com.avila.gapiforcapco.dtos;

import io.swagger.annotations.ApiModel;
import java.util.List;

@ApiModel(description = "Response DTO people list with the average mass of this group and a list of each person of " +
        "this group with it mass information.")
public class HumansPeopleAndAvMass {
    private String averageMass;
    private List<PersonAndMass> people;

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
