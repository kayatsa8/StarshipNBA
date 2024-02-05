package com.starship.nbamicroservice.controller;

import com.starship.nbamicroservice.log.Log;
import com.starship.nbamicroservice.model.gnewsModel.Article;
import com.starship.nbamicroservice.model.players.Player;
import com.starship.nbamicroservice.model.teams.Team;
import com.starship.nbamicroservice.service.PlayerService;
import com.starship.nbamicroservice.service.TeamService;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static Logger logger = LogManager.getLogger(NBAController.class);

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private WebClient.Builder webClientBuilder;



    public NBAController(){
        logger.info("creating NBA controller");

//        teamService.init();
//        playerService.init();
    }

    @PostConstruct
    public void init(){
        logger.debug("init teams&players data");

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

        logger.debug("successful init");
    }

    @GetMapping("/nba/teams")
    public List<Team> getTeams(){
        logger.debug("returning teams");
        return teamService.findAll();
    }

    @PostMapping("/nba/teams")
    public void importTeams(){
        logger.debug("importing teams");
        teamService.init();
    }

    @GetMapping("/nba/players")
    public List<Player> getPlayers(){
        logger.debug("returning players");
        return playerService.findAll();
    }

    @PostMapping("/nba/players")
    public void importPlayers(){
        logger.debug("importing players");
        playerService.init();
    }

    @GetMapping("/news/top-headlines")
    public List<Article> getTopHeadlines(){
        logger.info("fetching top-headlines");
        return new Fetcher<Article>(null, webClientBuilder).fetch("http://localhost:8083/api/news/top-headlines",
                Article.class, new HashMap<>(), (root) -> root);//TODO change to lb

        //localhost:8083
    }








}
