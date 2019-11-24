package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Specie {
    @JsonProperty("name")
    private String name;
    @JsonProperty("people")
    private List<String> peopleUrl;

    public String getName() {
        return name;
    }

    public List<String> getPeopleUrl() {
        return peopleUrl;
    }
}
