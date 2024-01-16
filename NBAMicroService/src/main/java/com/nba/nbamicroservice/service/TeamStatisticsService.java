package com.nba.nbamicroservice.service;

import com.nba.nbamicroservice.fetcher.Fetcher;
import com.nba.nbamicroservice.model.team_statistics.TeamStatistics;
import com.nba.nbamicroservice.repository.TeamStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamStatisticsService extends NBAService{

    private TeamStatisticsRepository repository;


    public TeamStatisticsService(TeamStatisticsRepository repo){
        repository = repo;
    }

    public void init() {
        new Fetcher<TeamStatistics>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        TeamStatistics.class, getHeaders(), nbaDataExtractor());
    }
}
