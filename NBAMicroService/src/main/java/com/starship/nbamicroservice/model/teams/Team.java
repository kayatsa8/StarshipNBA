package com.starship.nbamicroservice.model.teams;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Document("Teams")
public class Team {

    @Id
    private int id;
    private String name;
    private String nickname;
    private String code;
    private String city;
    private String logo;
    private boolean allStar;
    private boolean nbaFranchise;
//    private Leagues leagues;
    private Map<String, League> leagues;


    @JsonCreator
    public Team(@JsonProperty("id") int id, @JsonProperty("name") String name,
                @JsonProperty("nickname") String nickname, @JsonProperty("code") String code,
                @JsonProperty("city") String city, @JsonProperty("logo") String logo,
                @JsonProperty("allStar") boolean allStar, @JsonProperty("nbaFranchise") boolean nbaFranchise,
                @JsonProperty("leagues") Map<String, League> leagues) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.code = code;
        this.city = city;
        this.logo = logo;
        this.allStar = allStar;
        this.nbaFranchise = nbaFranchise;
        this.leagues = leagues;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", logo='" + logo + '\'' +
                ", allStar=" + allStar +
                ", nbaFranchise=" + nbaFranchise +
                ", leagues=" + leagues +
                '}';
    }
}
