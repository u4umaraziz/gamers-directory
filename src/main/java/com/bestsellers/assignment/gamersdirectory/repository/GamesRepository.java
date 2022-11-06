package com.bestsellers.assignment.gamersdirectory.repository;

import com.bestsellers.assignment.gamersdirectory.entity.Games;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamesRepository extends CrudRepository<Games, Long> {
    Optional<Games> findByGameId(String id);

}
