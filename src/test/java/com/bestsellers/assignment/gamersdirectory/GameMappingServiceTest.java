package com.bestsellers.assignment.gamersdirectory;

import com.bestsellers.assignment.gamersdirectory.entity.GameMapping;
import com.bestsellers.assignment.gamersdirectory.entity.Games;
import com.bestsellers.assignment.gamersdirectory.entity.Player;
import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;
import com.bestsellers.assignment.gamersdirectory.repository.GameMappingRepository;
import com.bestsellers.assignment.gamersdirectory.service.GameMappingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class GameMappingServiceTest {

   /*@TestConfiguration
    static class GameMappingServiceImplTestContextConfiguration {

        @Bean
        public GameMappingService gameMappingService() {
           return new GameMappingServiceImpl();
        }
    }*/

    @Autowired
    GameMappingService gameMappingService;

    @MockBean
    private GameMappingRepository gameMappingRepository;

    private static final String gameUuid = "a08210c2-c6a3-4b00-a0e1-9f84cdef24d1";
    private static final String playerUuid = "a08210c2-c6a3-4b00-a0e1-9f84cdef24d0";


    @BeforeEach
    public void setUp() {

        mock(GameMapping.class);

        GameMapping gameMapping = new GameMapping().toBuilder()
                .gamerLevel(GamerLevelEnum.INVINCIBLE)
                .games(new Games().toBuilder()
                        .id(1L)
                        .build())
                .player(new Player().toBuilder()
                        .id(1L)
                        .build())
                .build();

          Mockito.when(gameMappingRepository.save(gameMapping)).thenThrow(DataIntegrityViolationException.class);

    }

    @Test
    public void whenGameAndPlayerIdsAlreadyLinked_thenExceptionShouldBeThrown() {
        assertThrows(DataIntegrityViolationException.class,
                () -> gameMappingService.linkGame(1L, 1L, GamerLevelEnum.INVINCIBLE));
    }

}
