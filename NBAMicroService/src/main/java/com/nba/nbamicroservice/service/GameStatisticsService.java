package com.nba.nbamicroservice.service;

import com.nba.nbamicroservice.fetcher.Fetcher;
import com.nba.nbamicroservice.model.game_statistics.GameStatistics;
import com.nba.nbamicroservice.repository.GameStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticsService extends NBAService{

    private GameStatisticsRepository repository;


    public GameStatisticsService(GameStatisticsRepository repo){
        repository = repo;
    }

    public void init(){
        new Fetcher<GameStatistics>(repository)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        GameStatistics.class, getHeaders(), nbaDataExtractor());
    }


}
