package com.luizalabs.service;

import java.util.ArrayList;
import java.util.List;
import com.luizalabs.models.Game;
import com.luizalabs.models.LogFile;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class GameService {

	private boolean readed = false;

	private final LogFile log;
	private Game actualGame;

	private final List<Game> gameList;

	public GameService(LogFile log) {
		this.gameList = new ArrayList<>();
		this.log = log;
	}

	public List<Game> getGameList() {

		if (!this.readed) {
			readLog();
		}

		return gameList;
	}

	private void readLog() {

		configureNewGame();

		for (String rawLine : log) {
			Row line = GameSetup.readLine(rawLine);

			actualGame.addEvent(line);

			if (actualGame.isFinished()) {
				configureNewGame();
			}

		}

		readed = true;

	}

	private void configureNewGame() {
		if (actualGame != null) {
			gameList.add(actualGame);
		}
		actualGame = new Game();
	}

}
