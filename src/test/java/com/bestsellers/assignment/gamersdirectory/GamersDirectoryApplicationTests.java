package com.bestsellers.assignment.gamersdirectory;

import com.bestsellers.assignment.gamersdirectory.entity.Player;
import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;
import com.bestsellers.assignment.gamersdirectory.exception.NoGameFoundException;
import com.bestsellers.assignment.gamersdirectory.exception.NoPlayerFoundException;
import com.bestsellers.assignment.gamersdirectory.model.LinkGameRequestDTO;
import com.bestsellers.assignment.gamersdirectory.service.GameService;
import com.bestsellers.assignment.gamersdirectory.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	private static final String gameUuid = "a08210c2-c6a3-4b00-a0e1-9f84cdef24d1";
	private static final String playerUuid = "e08210c2-c6a3-4b00-a0e1-9f84cdef24d0";

	@Test
	public void whenGetPlayers_thenReturnGamerExists() throws Exception {

		mvc.perform(get("/players").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Gamer1")));

	}

	@Test
	public void registerPlayer_ThenReturnCreatedResponse() throws Exception
	{
		Player player = new Player().toBuilder()
				.name("Umar")
				.location("Pakistan")
				.build();

		mvc.perform(post("/register").content(asJsonString(player))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mvc.perform(get("/players")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(10)));
	}

	@Test
	public void linkAlreadyLinkedPlayer_ThenThrowError() throws Exception
	{
		LinkGameRequestDTO linkGameRequestDTO = new LinkGameRequestDTO().toBuilder()
				.gameId(gameUuid)
				.playerId(playerUuid)
				.level(GamerLevelEnum.INVINCIBLE)
				.build();

		mvc.perform(post("/link").content(asJsonString(linkGameRequestDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());

	}

	@Test
	public void linkPlayer_ThenReturnSuccess() throws Exception
	{
		LinkGameRequestDTO linkGameRequestDTO = new LinkGameRequestDTO().toBuilder()
				.gameId(gameUuid)
				.playerId(playerUuid)
				.level(GamerLevelEnum.INVINCIBLE)
				.build();

		mvc.perform(post("/link").content(asJsonString(linkGameRequestDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

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

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
