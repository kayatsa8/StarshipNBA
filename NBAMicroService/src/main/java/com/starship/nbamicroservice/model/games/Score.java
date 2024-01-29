package com.starship.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {

    private int win;
    private int loss;
    private Series series;
    private String[] lineScore;
    private int points;


    @JsonCreator
    public Score(@JsonProperty("win") int win,
                 @JsonProperty("loss") int loss,
                 @JsonProperty("series") Series series,
                 @JsonProperty("linescore") String[] lineScore,
                 @JsonProperty("points") int points) {
        this.win = win;
        this.loss = loss;
        this.series = series;
        this.lineScore = lineScore;
        this.points = points;
    }
}
