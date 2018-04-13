package com.luizalabs.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.github.javafaker.Faker;
import com.luizalabs.models.Game;
import com.luizalabs.models.Player;
import com.luizalabs.service.GameService;

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
	public void getGamesInfo() throws Exception {

		List<Game> gameList = new ArrayList<>();

		List<Player> playerList = new ArrayList<>();
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName()));
		playerList.add(new Player(faker.number().randomDigit(), faker.name().firstName()));

		Map<Integer, Integer> killMap = new HashMap<>();
		killMap.put(playerList.get(0).getId(), faker.number().randomDigit());
		killMap.put(playerList.get(1).getId(), faker.number().randomDigit());
		killMap.put(playerList.get(2).getId(), faker.number().randomDigit());
		killMap.put(playerList.get(3).getId(), faker.number().randomDigit());

		Game game = new Game();
		game.setPlayers(playerList);
		game.setTotalKills(faker.number().randomDigit());
		game.setKills(killMap);

		gameList.add(game);

		Mockito.when(gameService.getGameList()).thenReturn(gameList);

		this.mockMvc.perform(get("/luizalabs/game-info")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

}
