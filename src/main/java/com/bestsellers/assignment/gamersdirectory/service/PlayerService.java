package com.bestsellers.assignment.gamersdirectory.service;

import com.bestsellers.assignment.gamersdirectory.entity.Player;
import com.bestsellers.assignment.gamersdirectory.model.PlayerRequestDTO;
import com.bestsellers.assignment.gamersdirectory.model.LinkGameRequestDTO;

import java.util.List;

public interface PlayerService {

    String registerPlayer(PlayerRequestDTO gamerRequestDTO);
    void linkGame(LinkGameRequestDTO linkGameRequestDTO);
    List<Player> getAllPlayers();

}
