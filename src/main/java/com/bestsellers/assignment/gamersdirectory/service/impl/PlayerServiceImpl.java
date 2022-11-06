package com.bestsellers.assignment.gamersdirectory.service.impl;

import com.bestsellers.assignment.gamersdirectory.entity.Player;
import com.bestsellers.assignment.gamersdirectory.exception.NoPlayerFoundException;
import com.bestsellers.assignment.gamersdirectory.model.LinkGameRequestDTO;
import com.bestsellers.assignment.gamersdirectory.model.PlayerRequestDTO;
import com.bestsellers.assignment.gamersdirectory.repository.PlayersRepository;
import com.bestsellers.assignment.gamersdirectory.service.GameMappingService;
import com.bestsellers.assignment.gamersdirectory.service.GameService;
import com.bestsellers.assignment.gamersdirectory.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private GameMappingService gameMappingService;

    @Autowired
    private GameService gameService;

    @Override
    public String registerPlayer(PlayerRequestDTO gamerRequestDTO) {
        Player player = new Player().toBuilder()
                .playerId(UUID.randomUUID().toString())
                .location(gamerRequestDTO.getLocation())
                .name(gamerRequestDTO.getName())
                .build();
        return playersRepository.save(player).getPlayerId();
    }

    @Override
    public void linkGame(LinkGameRequestDTO linkGameRequestDTO) {

        Long playerId = getGamerById(linkGameRequestDTO.getPlayerId()).getId();
        Long gameId = gameService.getGameById(linkGameRequestDTO.getGameId()).getId();

        gameMappingService.linkGame(gameId, playerId, linkGameRequestDTO.getLevel());
    }

    private Player getGamerById(String gamerId) {
        Optional<Player> gamerOptional = playersRepository.findByPlayerId(gamerId);
        gamerOptional.orElseThrow(() -> new NoPlayerFoundException());
        return gamerOptional.get();
    }

    @Override
    public List<Player> getAllPlayers() {
        return IterableUtils.toList(playersRepository.findAll());
    }
}
