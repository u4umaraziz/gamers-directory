package com.bestsellers.assignment.gamersdirectory.repository;

import com.bestsellers.assignment.gamersdirectory.entity.RegisteredGamesView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterdGamesViewRepository extends ReadOnlyRepository<RegisteredGamesView, Long> {

    @Query(value = "SELECT gm.id, g.name as \"game_name\", p.name as \"player_name\", p.location, gm.gamer_level as \"gamer_level\"\n" +
            "FROM PLAYER p\n" +
            "         INNER JOIN GAME_MAPPING gm ON p.id = gm.player_id\n" +
            "         INNER JOIN GAMES g ON g.id = gm.game_id\n" +
            "         GROUP BY g.name, p.name\n" +
            "         HAVING gm.gamer_level =:gameLevel", nativeQuery = true)
    List<RegisteredGamesView> findRegisteredGamesViewByGameLevel(String gameLevel);


}
