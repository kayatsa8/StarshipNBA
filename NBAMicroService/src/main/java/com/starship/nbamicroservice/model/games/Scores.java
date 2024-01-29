package com.starship.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Scores {
    private Score visitors;
    private Score home;


    @JsonCreator
    public Scores(@JsonProperty("visitors") Score visitors,
                  @JsonProperty("home") Score home) {
        this.visitors = visitors;
        this.home = home;
    }
}
