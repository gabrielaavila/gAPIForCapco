package com.avila.gapiforcapco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsPaged<T> {
        @JsonProperty("count")
        private int count;
        @JsonProperty("next")
        private String next;
        @JsonProperty("previous")
        private String previous;
        @JsonProperty("results")
        private List<T> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<T> getResults() {
        return results;
    }

    public boolean hasNext(){
        return this.next != null;
    }
}
