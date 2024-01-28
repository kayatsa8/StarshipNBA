package com.nba.nbamicroservice.service;

import com.nba.nbamicroservice.model.games.Game;
import com.nba.nbamicroservice.repository.GameRepository;
import org.example.commons.Fetcher;
import org.springframework.stereotype.Service;

@Service
public class GameService extends NBAService{

    private final GameRepository repository;


    public GameService(GameRepository repository){
        this.repository = repository;
    }

    public void init(){
        new Fetcher<>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        Game.class, getHeaders(), nbaDataExtractor());
    }

}
