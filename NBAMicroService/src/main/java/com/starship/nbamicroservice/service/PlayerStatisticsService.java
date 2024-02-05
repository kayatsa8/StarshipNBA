package com.starship.nbamicroservice.service;

import com.starship.nbamicroservice.model.player_statistics.PlayerStatistics;
import com.starship.nbamicroservice.repository.PlayerStatisticsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsService extends NBAService{

    private static Logger logger = LogManager.getLogger(PlayerStatisticsService.class);

    private final PlayerStatisticsRepository repository;


    public PlayerStatisticsService(PlayerStatisticsRepository repo){
        repository = repo;

        logger.info("creating player statistics service");
    }

    public void init() {
        logger.info("fetching player statistics data from api");

        new Fetcher<>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        PlayerStatistics.class, getHeaders(), nbaDataExtractor());

        logger.info("player statistics data fetched");
    }
}
