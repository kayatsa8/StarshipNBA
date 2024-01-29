package com.starship.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document("Games")
public class Game {

    @Id
    private long id;
    private String league;
    private int season;
    private Date date;
    private int stage;
    private Status status;
    private Periods periods;
    private Arena arena;
    private Teams teams;
    private Scores scores;
    private String[] officials;
    private int timesTied;
    private int leadChanges;
    private Object nugget;


    @JsonCreator
    public Game(@JsonProperty("id") long id, @JsonProperty("league") String league,
                @JsonProperty("season") int season, @JsonProperty("date") Date date,
                @JsonProperty("stage") int stage, @JsonProperty("status") Status status,
                @JsonProperty("periods") Periods periods, @JsonProperty("arena") Arena arena,
                @JsonProperty("teams") Teams teams, @JsonProperty("scores") Scores scores,
                @JsonProperty("officials") String[] officials, @JsonProperty("timesTied") int timesTied,
                @JsonProperty("leadChanges") int leadChanges, @JsonProperty("nugget") Object nugget) {
        this.id = id;
        this.league = league;
        this.season = season;
        this.date = date;
        this.stage = stage;
        this.status = status;
        this.periods = periods;
        this.arena = arena;
        this.teams = teams;
        this.scores = scores;
        this.officials = officials;
        this.timesTied = timesTied;
        this.leadChanges = leadChanges;
        this.nugget = nugget;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", league='" + league + '\'' +
                ", season=" + season +
                ", date=" + date +
                ", stage=" + stage +
                ", status=" + status +
                ", periods=" + periods +
                ", arena=" + arena +
                ", teams=" + teams +
                ", scores=" + scores +
                ", officials=" + Arrays.toString(officials) +
                ", timesTied=" + timesTied +
                ", leadChanges=" + leadChanges +
                ", nugget=" + nugget +
                '}';
    }
}
