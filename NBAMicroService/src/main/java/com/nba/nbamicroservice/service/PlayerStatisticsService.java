package com.nba.nbamicroservice.service;

import com.nba.nbamicroservice.model.player_statistics.PlayerStatistics;
import com.nba.nbamicroservice.repository.PlayerStatisticsRepository;
import org.example.commons.Fetcher;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsService extends NBAService{

    private final PlayerStatisticsRepository repository;


    public PlayerStatisticsService(PlayerStatisticsRepository repo){
        repository = repo;
    }

    public void init() {
        new Fetcher<>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        PlayerStatistics.class, getHeaders(), nbaDataExtractor());
    }
}
