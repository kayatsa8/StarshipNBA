package com.starship.nbamicroservice.service.interfaces;

import com.starship.nbamicroservice.model.players.Player;

import java.util.List;

public interface PlayerService extends BasicNBAService{

    List<Player> findAll();

}
