package com.nba.nbamicroservice.model.game_statistics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nba.nbamicroservice.model.games.Team;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("GameStatistics")
public class GameStatistics {

    private Team team;
    private Statistic[] statistics;


    @JsonCreator
    public GameStatistics(@JsonProperty("team") Team team,
                          @JsonProperty("statistics") Statistic[] statistics) {
        this.team = team;
        this.statistics = statistics;
    }
}
