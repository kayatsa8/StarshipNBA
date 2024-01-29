package com.starship.starshipUI.model.nba.teams;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class League {

    private String conference;
    private String division;


    @JsonCreator
    public League(@JsonProperty("conference") String conference,
                  @JsonProperty("division") String division){
        this.conference = conference;
        this.division = division;
    }

    @Override
    public String toString() {
        return "{" +
                "conference='" + conference + '\'' +
                ", division='" + division + '\'' +
                '}';
    }
}
