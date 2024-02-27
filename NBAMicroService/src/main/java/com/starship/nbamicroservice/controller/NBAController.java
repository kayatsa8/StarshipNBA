package com.starship.nbamicroservice.controller;

import com.starship.nbamicroservice.log.Log;
import com.starship.nbamicroservice.model.players.Player;
import com.starship.nbamicroservice.model.teams.Team;
import com.starship.nbamicroservice.service.interfaces.PlayerService;
import com.starship.nbamicroservice.service.interfaces.TeamService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
    private MeterRegistry registery;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;



    public NBAController(){
        logger.info("creating NBA controller");
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
        registery.gauge("documents.in.db", Tags.of("collection", "teams"), teamService, teamService ->teamService.findAll().size());
        registery.gauge("documents.in.db", Tags.of("collection", "players"), playerService, playerService ->playerService.findAll().size());
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
    public String getTopHeadlines(){//TODO fix changes
        logger.info("fetching top-headlines");
        List<ServiceInstance> instances = discoveryClient.getInstances("ms-news");
        if(instances.isEmpty()){
            logger.error("ms-news service not found");
            return "ms-news service not found";
        }
        return restTemplate.getForObject(instances.get(0).getUri()+"/api/news/top-headlines", String.class);
    }








}
