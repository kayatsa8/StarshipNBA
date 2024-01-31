package com.starship.nbamicroservice.service;

import com.starship.nbamicroservice.model.game_statistics.GameStatistics;
import com.starship.nbamicroservice.repository.GameStatisticsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticsService extends NBAService{

    private static Logger logger = LogManager.getLogger(GameStatisticsService.class);

    private final GameStatisticsRepository repository;


    public GameStatisticsService(GameStatisticsRepository repo){
        repository = repo;

        logger.info("creating game statistics service");
    }

    public void init(){
        logger.info("fetching game statistics data from api");

        new Fetcher<>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        GameStatistics.class, getHeaders(), nbaDataExtractor());

        logger.info("game statistics data fetched");
    }


}
