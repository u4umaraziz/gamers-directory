package com.bestsellers.assignment.gamersdirectory.repository;

import com.bestsellers.assignment.gamersdirectory.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayersRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByPlayerId(String id);
}
