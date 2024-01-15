package com.nba.nbamicroservice.model.team_statistics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("TeamStatistics")
public class TeamStatistics {

    private int games;
    private int fastBreakPoints;
    private int pointsInPaint;
    private int biggestLead;
    private int secondChancePoints;
    private int pointsOffTurnovers;
    private int longestRun;
    private int points;
    private int fgm;
    private int fga;
    private String fgp;
    private int ftm;
    private int fta;
    private String ftp;
    private int tpm;
    private int tpa;
    private String tpp;
    private int offReb;
    private int defReb;
    private int totReb;
    private int assists;
    private int pFouls;
    private int steals;
    private int turnovers;
    private int blocks;
    private int plusMinus;


    @JsonCreator
    public TeamStatistics(@JsonProperty("games") int games, @JsonProperty("fastBreakPoints") int fastBreakPoints,
                          @JsonProperty("pointsInPaint") int pointsInPaint, @JsonProperty("biggestLead") int biggestLead,
                          @JsonProperty("secondChancePoints") int secondChancePoints,
                          @JsonProperty("pointsOffTurnovers") int pointsOffTurnovers,
                          @JsonProperty("longestRun") int longestRun, @JsonProperty("points") int points,
                          @JsonProperty("fgm") int fgm, @JsonProperty("fga") int fga,
                          @JsonProperty("fgp") String fgp, @JsonProperty("ftm") int ftm,
                          @JsonProperty("fta") int fta, @JsonProperty("ftp") String ftp,
                          @JsonProperty("tpm") int tpm, @JsonProperty("tpa") int tpa,
                          @JsonProperty("tpp") String tpp, @JsonProperty("offReb") int offReb,
                          @JsonProperty("defReb") int defReb, @JsonProperty("totReb") int totReb,
                          @JsonProperty("assists") int assists, @JsonProperty("pFouls") int pFouls,
                          @JsonProperty("steals") int steals, @JsonProperty("turnovers") int turnovers,
                          @JsonProperty("blocks") int blocks, @JsonProperty("plusMinus") int plusMinus) {
        this.games = games;
        this.fastBreakPoints = fastBreakPoints;
        this.pointsInPaint = pointsInPaint;
        this.biggestLead = biggestLead;
        this.secondChancePoints = secondChancePoints;
        this.pointsOffTurnovers = pointsOffTurnovers;
        this.longestRun = longestRun;
        this.points = points;
        this.fgm = fgm;
        this.fga = fga;
        this.fgp = fgp;
        this.ftm = ftm;
        this.fta = fta;
        this.ftp = ftp;
        this.tpm = tpm;
        this.tpa = tpa;
        this.tpp = tpp;
        this.offReb = offReb;
        this.defReb = defReb;
        this.totReb = totReb;
        this.assists = assists;
        this.pFouls = pFouls;
        this.steals = steals;
        this.turnovers = turnovers;
        this.blocks = blocks;
        this.plusMinus = plusMinus;
    }
}
