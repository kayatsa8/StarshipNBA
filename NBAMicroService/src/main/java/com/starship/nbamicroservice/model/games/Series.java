package com.starship.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Series {
    private int win;
    private int loss;


    @JsonCreator
    public Series(@JsonProperty("win") int win, @JsonProperty("loss") int loss) {
        this.win = win;
        this.loss = loss;
    }
}
