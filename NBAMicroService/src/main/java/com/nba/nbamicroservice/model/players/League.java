package com.nba.nbamicroservice.model.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class League {

    private int jersey;
    private boolean active;
    private String pos;


    @JsonCreator
    public League(@JsonProperty("jersey") int jersey,
                  @JsonProperty("active") boolean active,
                  @JsonProperty("pos") String pos) {
        this.jersey = jersey;
        this.active = active;
        this.pos = pos;
    }

    public int getJersey() {
        return jersey;
    }

    public void setJersey(int jersey) {
        this.jersey = jersey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
