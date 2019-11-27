package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Request DTO Person with name, birth year, mass information and list of url resources " +
        "of the movies it performed.")
public class Person {

    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private String birth_year;
    @JsonProperty("mass")
    private String mass;
    @JsonProperty("films")
    private List<String> films;
    //private String url;


    public String getName() {
        return name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public String getMass() {
        return mass;
    }

    public List<String> getFilms() {
        return films;
    }
}
