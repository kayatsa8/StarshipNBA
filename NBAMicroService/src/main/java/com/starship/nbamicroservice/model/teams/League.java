package com.starship.nbamicroservice.model.teams;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class League {

    private String conference;
    private String division;


    @JsonCreator
    public League(@JsonProperty("conference") String conference,
                  @JsonProperty("division") String division){
        this.conference = conference;
        this.division = division;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
