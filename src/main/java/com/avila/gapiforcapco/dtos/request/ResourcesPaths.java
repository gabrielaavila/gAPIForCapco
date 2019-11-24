package com.avila.gapiforcapco.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourcesPaths {
    private String films;
    private String people;
    private String planets;
    private String species;
    private String starships;
    private String vehicles;

    public String getFilms() {
        return films;
    }

    public String getPeople() {
        return people;
    }

    public String getPlanets() {
        return planets;
    }

    public String getSpecies() {
        return species;
    }

    public String getStarships() {
        return starships;
    }

    public String getVehicles() {
        return vehicles;
    }
}
