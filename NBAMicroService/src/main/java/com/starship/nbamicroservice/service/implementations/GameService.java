package com.starship.nbamicroservice.service.implementations;

import com.starship.nbamicroservice.model.games.Game;
import com.starship.nbamicroservice.repository.GameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.starship.commons.Fetcher;

@Service
public class GameService extends NBAService {

    private static Logger logger = LogManager.getLogger(GameService.class);

    private GameRepository repository;


    public GameService(GameRepository repository){
        this.repository = repository;

        logger.info("creating game service");
    }

    public void init(){
        logger.info("fetching game data from api");

        new Fetcher<Game>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        Game.class, getHeaders(), nbaDataExtractor());

        logger.info("game data fetched");
    }

}
