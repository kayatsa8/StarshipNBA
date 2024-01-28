package com.starship.starshipUI.model.nba.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Birth {

    private String date;
    private String country;

    @JsonCreator
    public Birth(@JsonProperty("date") String date, @JsonProperty("country") String country) {
        this.date = date;
        this.country = country;
    }

}
