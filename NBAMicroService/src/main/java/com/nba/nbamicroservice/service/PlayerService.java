package com.nba.nbamicroservice.service;

import com.nba.nbamicroservice.fetcher.Fetcher;
import com.nba.nbamicroservice.model.players.Player;
import com.nba.nbamicroservice.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService extends NBAService{

    private PlayerRepository repository;


    public PlayerService(PlayerRepository repo){
        repository = repo;
    }


    public void init() {
        new Fetcher<Player>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        Player.class, getHeaders(), nbaDataExtractor());
    }

    public List<Player> findAll(){
        return repository.findAll();
    }
}
