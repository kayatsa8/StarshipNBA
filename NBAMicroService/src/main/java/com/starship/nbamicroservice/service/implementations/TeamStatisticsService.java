package com.starship.nbamicroservice.service.implementations;

import com.starship.nbamicroservice.model.team_statistics.TeamStatistics;
import com.starship.nbamicroservice.repository.TeamStatisticsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

@Service
public class TeamStatisticsService extends NBAService{

    private static Logger logger = LogManager.getLogger(TeamStatisticsService.class);

    private final TeamStatisticsRepository repository;


    public TeamStatisticsService(TeamStatisticsRepository repo){
        repository = repo;

        logger.info("creating team statistics service");
    }

    public void init() {
        logger.info("fetching team statistics data from api");

        new Fetcher<>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        TeamStatistics.class, getHeaders(), nbaDataExtractor());

        logger.info("team statistics data fetched");
    }
}
