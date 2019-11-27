package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Request DTO of a paged result.")
public class ResultsPaged<T> {
        @JsonProperty("count")
        private int count;
        @JsonProperty("next")
        private String next;
        @JsonProperty("previous")
        private String previous;
        @JsonProperty("results")
        private List<T> results;

    public String getNext() {
        return next;
    }

    public List<T> getResults() {
        return results;
    }

    public boolean hasNext(){
        return this.next != null;
    }

    public ResultsPaged() {
    }

    public ResultsPaged(int count, String next, String previous, List<T> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }
}
