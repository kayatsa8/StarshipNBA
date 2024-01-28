package com.starship.starshipUI.model.nba.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Player {

    private int id;
    private String firstName;
    private String lastName;
    private Birth birth;
    private NBA nba;
    private Height height;
    private Weight weight;
    private String college;
    private String affiliation;
    private Map<String, League> leagues;


    @JsonCreator
    public Player(@JsonProperty("id") int id, @JsonProperty("firstname") String firstName,
                  @JsonProperty("lastname") String lastName, @JsonProperty("birth") Birth birth,
                  @JsonProperty("nba") NBA nba, @JsonProperty("height") Height height,
                  @JsonProperty("weight") Weight weight, @JsonProperty("college") String college,
                  @JsonProperty("affiliation") String affiliation,
                  @JsonProperty("leagues") Map<String, League> leagues) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.nba = nba;
        this.height = height;
        this.weight = weight;
        this.college = college;
        this.affiliation = affiliation;
        this.leagues = leagues;
    }

    public String getLeaguesAsString(){
        StringBuilder leagueString = new StringBuilder();

        for(String league : leagues.keySet()){
            leagueString.append(league).append(", ");
        }

        return leagueString.delete(leagueString.length()-1, leagueString.length()).toString();
    }
}
