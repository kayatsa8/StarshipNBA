package com.starship.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Teams {
    private Team visitors;
    private Team home;


    @JsonCreator
    public Teams(@JsonProperty("visitors") Team visitors,
                 @JsonProperty("home") Team home) {
        this.visitors = visitors;
        this.home = home;
    }
}
