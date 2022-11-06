package com.bestsellers.assignment.gamersdirectory.service;

import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;

public interface GameMappingService {
    void linkGame(Long gameId, Long playerId, GamerLevelEnum level );
}
