package com.nba.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Arena {
    private String name;
    private String City;
    private String state;
    private String country;


    @JsonCreator
    public Arena(@JsonProperty("name") String name,
                 @JsonProperty("city") String city,
                 @JsonProperty("state") String state,
                 @JsonProperty("country") String country) {
        this.name = name;
        City = city;
        this.state = state;
        this.country = country;
    }
}
