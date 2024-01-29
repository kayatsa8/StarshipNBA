package com.starship.nbamicroservice.model.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NBA {

    private int start;
    private int pro;


    @JsonCreator
    public NBA(@JsonProperty("start") int start, @JsonProperty("pro") int pro) {
        this.start = start;
        this.pro = pro;
    }

}
