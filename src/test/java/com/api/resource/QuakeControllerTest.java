package com.api.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api.service.QuakeService;
import com.api.model.Game;

import com.github.javafaker.Faker;

public class QuakeControllerTest {

	private final Faker faker = new Faker();
	
	private MockMvc mockMvc;
	
	@Mock
	private QuakeService quakeService;
	
	@InjectMocks
	private QuakeController quakeController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(quakeController).build();
		
	}
	
	@Test
	public void getGames() throws Exception {

		Map<String, Game> gameMap = new TreeMap<>();
		
		String randomPlayer1 = faker.name().firstName();
		String randomPlayer2 = faker.name().firstName();
		String randomPlayer3 = faker.name().firstName();

		Object[] players = {randomPlayer1, randomPlayer2, randomPlayer3};
		
		Map<String, Integer> kills = new TreeMap<>();
		kills.put(randomPlayer1, faker.number().randomDigit());
		kills.put(randomPlayer2, faker.number().randomDigit());
		kills.put(randomPlayer3, faker.number().randomDigit());
		
		Game game = new Game();
		game.setPlayers(players);
		game.setKills(kills);
		game.setTotalKills(faker.number().randomDigit());

		gameMap.put("game_"+faker.number().randomDigit(), game);

		Mockito.when(quakeService.getGames()).thenReturn(gameMap);

		this.mockMvc.perform(get("/api/v1/games")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
	
	@Test
	public void getGame() throws Exception {
		
		Map<String, Game> gameMap = new TreeMap<>();

		String randomPlayer1 = faker.name().firstName();
		String randomPlayer2 = faker.name().firstName();
		String randomPlayer3 = faker.name().firstName();

		Object[] players = {randomPlayer1, randomPlayer2, randomPlayer3};

		int gameNumber = faker.number().randomDigit();
		
		Map<String, Integer> kills = new TreeMap<>();
		kills.put(randomPlayer1, faker.number().randomDigit());
		kills.put(randomPlayer2, faker.number().randomDigit());
		kills.put(randomPlayer3, faker.number().randomDigit());
		
		Game game = new Game();
		game.setNumber(gameNumber);
		game.setPlayers(players);
		game.setKills(kills);
		game.setTotalKills(faker.number().randomDigit());

		gameMap.put("game_"+gameNumber, game);

		Mockito.when(quakeService.getGame(gameNumber)).thenReturn(game);

		this.mockMvc.perform(get("/api/v1/game/" + gameNumber)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
	}
	
	@Test
	public void getGameNull() {
		
	}
	
}
