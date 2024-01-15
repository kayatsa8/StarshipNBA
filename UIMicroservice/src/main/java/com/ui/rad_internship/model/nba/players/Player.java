package com.ui.rad_internship.model.nba.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public NBA getNba() {
        return nba;
    }

    public void setNba(NBA nba) {
        this.nba = nba;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public Map<String, League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Map<String, League> leagues) {
        this.leagues = leagues;
    }

    public int getNbaStart(){
        return nba.getStart();
    }

    public int getNbaPro(){
        return nba.getPro();
    }

    public String getHeightInMeters(){
        return height.getMeters();
    }

    public String getWeightInKilograms(){
        return weight.getKilograms();
    }

    public String getLeaguesAsString(){
        StringBuilder leagueString = new StringBuilder();

        for(String league : leagues.keySet()){
            leagueString.append(league).append(", ");
        }

        return leagueString.delete(leagueString.length()-1, leagueString.length()).toString();
    }
}
