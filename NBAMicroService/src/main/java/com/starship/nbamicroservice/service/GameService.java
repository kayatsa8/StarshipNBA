package com.starship.nbamicroservice.service;

import com.starship.nbamicroservice.fetcher.Fetcher;
import com.starship.nbamicroservice.model.games.Game;
import com.starship.nbamicroservice.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService extends NBAService{

    private GameRepository repository;


    public GameService(GameRepository repository){
        this.repository = repository;
    }

    public void init(){
        new Fetcher<Game>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        Game.class, getHeaders(), nbaDataExtractor());
    }

}
