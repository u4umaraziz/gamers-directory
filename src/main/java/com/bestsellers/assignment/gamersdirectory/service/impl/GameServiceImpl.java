package com.bestsellers.assignment.gamersdirectory.service.impl;

import com.bestsellers.assignment.gamersdirectory.entity.Games;
import com.bestsellers.assignment.gamersdirectory.exception.NoGameFoundException;
import com.bestsellers.assignment.gamersdirectory.repository.GamesRepository;
import com.bestsellers.assignment.gamersdirectory.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    @Autowired
    private GamesRepository gamesRepository;


    @Override
    public Games getGameById(String gameId) {

        Optional<Games> gamesOptional = gamesRepository.findByGameId(gameId);
        gamesOptional.orElseThrow(() -> new NoGameFoundException());
        return gamesOptional.get();

    }

}
