package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Request DTO with all resource paths available in the SWAPI.")
public class ResourcesPaths {
    private String films;
    private String people;
    private String planets;
    private String species;
    private String starships;
    private String vehicles;

    public String getPeople() {
        return people;
    }

    public String getSpecies() {
        return species;
    }

}
