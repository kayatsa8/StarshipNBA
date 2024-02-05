package com.starship.nbamicroservice.service.implementations;

import com.starship.nbamicroservice.model.players.Player;
import com.starship.nbamicroservice.repository.PlayerRepository;
import com.starship.nbamicroservice.service.interfaces.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl extends NBAService implements PlayerService {

    private static Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    private final PlayerRepository repository;


    public PlayerServiceImpl(PlayerRepository repo){
        repository = repo;

        logger.info("creating player service");
    }


    public void init() {
        logger.info("fetching player data from api");

        new Fetcher<>(repository)
                .fetch("https://api-nba-v1.p.rapidapi.com/players",
                        Player.class, getHeaders(), nbaDataExtractor());

        logger.info("player data fetched");
    }

    public List<Player> findAll(){
        logger.debug("finding all player data");
        return repository.findAll();
    }
}
