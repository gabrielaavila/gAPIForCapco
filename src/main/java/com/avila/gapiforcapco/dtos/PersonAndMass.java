package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonAndMass {
    @JsonProperty("name")
    private String name;
    @JsonProperty("mass")
    private String mass;

    public String getName() {
        return name;
    }

    public String getMass() {
        return mass;
    }
}
