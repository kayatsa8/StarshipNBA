package com.starship.nbamicroservice.service;

import com.starship.nbamicroservice.fetcher.Fetcher;
import com.starship.nbamicroservice.model.player_statistics.PlayerStatistics;
import com.starship.nbamicroservice.repository.PlayerStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsService extends NBAService{

    private PlayerStatisticsRepository repository;


    public PlayerStatisticsService(PlayerStatisticsRepository repo){
        repository = repo;
    }

    public void init() {
        new Fetcher<PlayerStatistics>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        PlayerStatistics.class, getHeaders(), nbaDataExtractor());
    }
}
