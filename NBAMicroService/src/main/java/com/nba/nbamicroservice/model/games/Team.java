package com.nba.nbamicroservice.model.games;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {
    private int id;
    private String name;
    private String nickname;
    private String code;
    private String logo;


    @JsonCreator
    public Team(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("nickname") String nickname,
                @JsonProperty("code") String code,
                @JsonProperty("logo") String logo) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.code = code;
        this.logo = logo;
    }
}
