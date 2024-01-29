package com.starship.nbamicroservice.service;

import com.starship.nbamicroservice.model.game_statistics.GameStatistics;
import com.starship.nbamicroservice.repository.GameStatisticsRepository;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticsService extends NBAService{

    private final GameStatisticsRepository repository;


    public GameStatisticsService(GameStatisticsRepository repo){
        repository = repo;
    }

    public void init(){
        new Fetcher<>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        GameStatistics.class, getHeaders(), nbaDataExtractor());
    }


}
