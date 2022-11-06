package com.bestsellers.assignment.gamersdirectory.service.impl;

import com.bestsellers.assignment.gamersdirectory.entity.GameMapping;
import com.bestsellers.assignment.gamersdirectory.entity.Games;
import com.bestsellers.assignment.gamersdirectory.entity.Player;
import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;
import com.bestsellers.assignment.gamersdirectory.repository.GameMappingRepository;
import com.bestsellers.assignment.gamersdirectory.service.GameMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameMappingServiceImpl implements GameMappingService {

    @Autowired
    private GameMappingRepository gameMappingRepository;

    @Override
    public void linkGame(final Long gameId, final Long playerId, final GamerLevelEnum level) {

        final GameMapping gameMapping = new GameMapping().toBuilder()
                .gamerLevel(Objects.nonNull(level) ? level : GamerLevelEnum.INVINCIBLE)
                .games(new Games().toBuilder()
                        .id(gameId)
                        .build())
                .player(new Player().toBuilder()
                        .id(playerId)
                        .build())
                .build();

        gameMappingRepository.save(gameMapping);
    }
}
