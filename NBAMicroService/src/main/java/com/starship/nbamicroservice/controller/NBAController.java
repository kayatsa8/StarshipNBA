package com.starship.nbamicroservice.controller;

import com.starship.nbamicroservice.log.Log;
import com.starship.nbamicroservice.model.gnewsModel.Article;
import com.starship.nbamicroservice.model.players.Player;
import com.starship.nbamicroservice.model.teams.Team;
import com.starship.nbamicroservice.service.PlayerService;
import com.starship.nbamicroservice.service.TeamService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.annotation.PostConstruct;
import org.starship.commons.Fetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NBAController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private MeterRegistry registery;



    public NBAController(){
    }

    @PostConstruct
    public void init(){
        if (teamService.findAll().isEmpty()) {
            System.out.println("importing teams - database is empty");
            Log.info("importing teams - database is empty");
            importTeams();
        }
        if(playerService.findAll().isEmpty()){
            System.out.println("importing teams - database is empty");
            Log.info("importing teams - database is empty");
            importPlayers();
        }
        registery.gauge("documents.in.db", Tags.of("collection", "teams"), teamService, teamService ->teamService.findAll().size());
        registery.gauge("documents.in.db", Tags.of("collection", "players"), playerService, playerService ->playerService.findAll().size());
    }

    @GetMapping("/nba/teams")
    public List<Team> getTeams(){
        Log.info("fetching teams");
        return teamService.findAll();
    }

    @PostMapping("/nba/teams")
    public void importTeams(){
        Log.info("importing teams");
        teamService.init();
    }

    @GetMapping("/nba/players")
    public List<Player> getPlayers(){
        Log.info("fetching players");
        return playerService.findAll();
    }

    @PostMapping("/nba/players")
    public void importPlayers(){
        Log.info("importing players");
        playerService.init();
    }

    @GetMapping("/news/top-headlines")
    public List<Article> getTopHeadlines(){
        Log.info("fetching top-headlines");
        return new Fetcher<Article>(null, webClientBuilder).fetch("http://localhost:8083/api/news/top-headlines",
                Article.class, new HashMap<>(), (root) -> root);//TODO change to lb

        //localhost:8083
    }








}
