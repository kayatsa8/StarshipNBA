package com.nba.nbamicroservice.model.player_statistics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private int id;
    private String firstName;
    private String lastName;


    @JsonCreator
    public Player(@JsonProperty("id") int id, @JsonProperty("firstname") String firstName,
                  @JsonProperty("lastname") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
