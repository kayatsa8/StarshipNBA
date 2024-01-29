package com.starship.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Date {

    private String start;
    private String end;
    private String duration;

    @JsonCreator
    public Date(@JsonProperty("start") String start,
                @JsonProperty("end") String end,
                @JsonProperty("duration") String duration){
        this.start = start;
        this.end = end;
        this.duration = duration;
    }
}
