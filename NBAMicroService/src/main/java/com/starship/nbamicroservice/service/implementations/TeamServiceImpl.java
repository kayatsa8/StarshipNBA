package com.starship.nbamicroservice.service.implementations;

import com.starship.nbamicroservice.model.teams.Team;
import com.starship.nbamicroservice.repository.TeamRepository;
import com.starship.nbamicroservice.service.interfaces.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl extends NBAService implements TeamService {

    private static Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    private final TeamRepository repository;


    public TeamServiceImpl(TeamRepository repo){
        repository = repo;

        logger.info("creating team service");
    }

    public void init(){
        logger.info("fetching team data from api");

        new Fetcher<>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        Team.class, getHeaders(), nbaDataExtractor());

        logger.info("team data fetched");
    }

    public List<Team> findAll(){
        logger.debug("finding all teams");
        return repository.findAll();
    }

}
