package com.starship.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

    private Object clock;
    private boolean halftime;
    private int _short;
    private String _long;


    @JsonCreator
    public Status(@JsonProperty("clock") Object clock,
                  @JsonProperty("halftime") boolean halftime,
                  @JsonProperty("short") int _short,
                  @JsonProperty("long") String _long) {
        this.clock = clock;
        this.halftime = halftime;
        this._short = _short;
        this._long = _long;
    }
}
