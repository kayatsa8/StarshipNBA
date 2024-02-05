package com.starship.nbamicroservice.service.interfaces;

import com.starship.nbamicroservice.model.teams.Team;

import java.util.List;

public interface TeamService extends BasicNBAService{

    List<Team> findAll();

}
