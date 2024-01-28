package com.starship.nbamicroservice.model.player_statistics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starship.nbamicroservice.model.games.Team;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PlayerStatistics")
public class PlayerStatistics {

    private Player player;
    private Team team;
    private Game game;
    private int points;
    private String pos;
    private String min;
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
    private String plusMinus;
    private String comment;


    @JsonCreator
    public PlayerStatistics(@JsonProperty("player") Player player, @JsonProperty("team") Team team,
                            @JsonProperty("game") Game game, @JsonProperty("points") int points,
                            @JsonProperty("pos") String pos, @JsonProperty("min") String min,
                            @JsonProperty("fgm") int fgm, @JsonProperty("fga") int fga,
                            @JsonProperty("fgp") String fgp, @JsonProperty("ftm") int ftm,
                            @JsonProperty("fta") int fta, @JsonProperty("ftp") String ftp,
                            @JsonProperty("tpm") int tpm, @JsonProperty("tpa") int tpa,
                            @JsonProperty("tpp") String tpp, @JsonProperty("offReb") int offReb,
                            @JsonProperty("defReb") int defReb, @JsonProperty("totReb") int totReb,
                            @JsonProperty("assists") int assists, @JsonProperty("pFouls") int pFouls,
                            @JsonProperty("steals") int steals, @JsonProperty("turnovers") int turnovers,
                            @JsonProperty("blocks") int blocks, @JsonProperty("plusMinus") String plusMinus,
                            @JsonProperty("comment") String comment) {
        this.player = player;
        this.team = team;
        this.game = game;
        this.points = points;
        this.pos = pos;
        this.min = min;
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
        this.comment = comment;
    }
}
