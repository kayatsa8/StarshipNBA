package com.nba.nbamicroservice.service;

import com.nba.nbamicroservice.model.teams.Team;
import com.nba.nbamicroservice.repository.TeamRepository;
import org.example.commons.Fetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService extends NBAService{

    private TeamRepository repository;


    public TeamService(TeamRepository repo){
        repository = repo;
    }

    public void init(){
        new Fetcher<Team>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        Team.class, getHeaders(), nbaDataExtractor());
    }

    public List<Team> findAll(){
        return repository.findAll();
    }

}
