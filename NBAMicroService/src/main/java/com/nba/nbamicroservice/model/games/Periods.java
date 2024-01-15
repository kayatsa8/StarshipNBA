package com.nba.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Periods {

    private int current;
    private int total;
    private boolean endOfPeriod;

    @JsonCreator
    public Periods(@JsonProperty("current") int current,
                   @JsonProperty("total") int total,
                   @JsonProperty("endOfPeriod") boolean endOfPeriod) {
        this.current = current;
        this.total = total;
        this.endOfPeriod = endOfPeriod;
    }
}
