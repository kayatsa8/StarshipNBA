package com.starship.nbamicroservice.model.player_statistics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

    private int id;


    @JsonCreator
    public Game(@JsonProperty("id") int id){
        this.id = id;
    }

}
