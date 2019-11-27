package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "DTO of a Specie with name and list of url resources of all people from that specie.")
public class Specie {
    @JsonProperty("name")
    private String name;
    @JsonProperty("people")
    private List<String> peopleUrl;

    public List<String> getPeopleUrl() {
        return peopleUrl;
    }
}
