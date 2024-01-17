package com.nba.nbamicroservice.controller;

import com.nba.nbamicroservice.fetcher.Fetcher;
import com.nba.nbamicroservice.log.Log;
import com.nba.nbamicroservice.model.gnewsModel.Article;
import com.nba.nbamicroservice.model.players.Player;
import com.nba.nbamicroservice.model.teams.Team;
import com.nba.nbamicroservice.service.PlayerService;
import com.nba.nbamicroservice.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NBAController {

    private TeamService teamService;
    private PlayerService playerService;
    @Autowired
    private WebClient.Builder webClientBuilder;



    public NBAController(TeamService teamService, PlayerService playerService){
        this.teamService = teamService;
        this.playerService = playerService;

//        teamService.init();
//        playerService.init();
    }

    @GetMapping("/nba/teams")
    public List<Team> getTeams(){
        Log.info("fetching teams");
        return teamService.findAll();
    }

    @GetMapping("/nba/players")
    public List<Player> getPlayers(){
        Log.info("fetching players");
        return playerService.findAll();
    }

    @GetMapping("/news/top-headlines")
    public List<Article> getTopHeadlines(){
        Log.info("fetching top-headlines");
        return new Fetcher<Article>(null, webClientBuilder).fetch("http://localhost:8083/api/news/top-headlines",
                Article.class, new HashMap<>(), (root) -> root);

        //localhost:8083
    }





}
