package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private String birth_year;
    @JsonProperty("mass")
    private String mass;
    @JsonProperty("films")
    private ArrayList<String> films;
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

    public ArrayList<String> getFilms() {
        return films;
    }
}
