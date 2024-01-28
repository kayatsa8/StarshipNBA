package com.starship.nbamicroservice.model.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
