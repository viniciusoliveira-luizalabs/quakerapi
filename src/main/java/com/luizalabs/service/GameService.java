package com.luizalabs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.models.Game;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
@Service
public class GameService {

	private final ProcessFile log;
	private Game actualGame;

	private final List<Game> gameList;

	@Autowired
	public GameService(ProcessFile log) {
		this.gameList = new ArrayList<>();
		this.log = log;
	}

	public List<Game> getGameList() {

		readLog();

		return gameList;
	}

	private void readLog() {

		configureNewGame();

		for (String line : log) {
			Row row = GameSetup.readLine(line);

			actualGame.addEvent(row);

			if (actualGame.isFinished()) {
				configureNewGame();
			}

		}


	}

	private void configureNewGame() {
		if (actualGame != null) {
			gameList.add(actualGame);
		}
		actualGame = new Game();
	}

}
