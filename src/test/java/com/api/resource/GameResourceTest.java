package com.api.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api.models.Player;
import com.api.resource.GameResource;
import com.api.service.Game;
import com.api.service.GameService;
import com.github.javafaker.Faker;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GameResourceTest {

	private final Faker faker = new Faker();

	private MockMvc mockMvc;

	@Mock
	private GameService gameService;

	@InjectMocks
	private GameResource gameResource;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(gameResource).build();
	}

	@Test
	public void getGames() throws Exception {

		List<Game> gameList = new ArrayList<>();

		List<Player> playerList = new ArrayList<>();
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));

		Game game = new Game(faker.number().randomDigit());
		game.setPlayers(playerList);
		game.setTotalKills(faker.number().randomDigit());

		gameList.add(game);

		Mockito.when(gameService.getGameList()).thenReturn(gameList);

		this.mockMvc.perform(get("/api/v1/games")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
	
	@Test
	public void getGame() throws Exception {
		
		List<Game> gameList = new ArrayList<>();

		List<Player> playerList = new ArrayList<>();
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName(), faker.number().randomDigit()));

		int gameNumber = faker.number().randomDigit();
		
		Game game = new Game(gameNumber);
		game.setPlayers(playerList);
		game.setTotalKills(faker.number().randomDigit());

		gameList.add(game);

		Mockito.when(gameService.getGameByNumber(gameNumber)).thenReturn(game);

		this.mockMvc.perform(get("/api/v1/game/" + gameNumber)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
	}

}
