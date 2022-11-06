package com.bestsellers.assignment.gamersdirectory;

import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;
import com.bestsellers.assignment.gamersdirectory.exception.NoGameFoundException;
import com.bestsellers.assignment.gamersdirectory.exception.NoPlayerFoundException;
import com.bestsellers.assignment.gamersdirectory.model.LinkGameRequestDTO;
import com.bestsellers.assignment.gamersdirectory.service.GameService;
import com.bestsellers.assignment.gamersdirectory.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class GamersDirectoryApplicationTests {


	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameService gameService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void whenGetPlayers_thenReturnJsonArray() throws Exception {

		mvc.perform(get("/players").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(9)))
				.andExpect(jsonPath("$[0].name", is("Gamer1")));

	}

	@Test
	public void testIfGameNoGameExists_thenThrowError() {
		Assertions.assertThrows(NoGameFoundException.class, () -> gameService.getGameById("1"));
	}

	@Test
	public void testIfNoPlayerExists_thenThrowError() {
		LinkGameRequestDTO linkGameRequestDTO = new LinkGameRequestDTO().toBuilder()
				.gameId("1")
				.playerId("1")
				.level(GamerLevelEnum.INVINCIBLE)
				.build();
		Assertions.assertThrows(NoPlayerFoundException.class, () -> playerService.linkGame(linkGameRequestDTO));
	}
}
