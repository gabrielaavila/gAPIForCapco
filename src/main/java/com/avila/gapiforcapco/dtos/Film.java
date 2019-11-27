package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "DTO film with title information.")
public class Film {
    @JsonProperty("title")
    private String title;

    public String getTitle() {
        return title;
    }
}
