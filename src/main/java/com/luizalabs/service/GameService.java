package com.luizalabs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.models.Row;

/**
 * @author Ivo
 * 
 * Classe de serviços do jogo
 *
 */
@Service
public class GameService {

	private final ProcessFile log;
	private Game actualGame;
	private int gameNumber = 1;

	private final List<Game> gameList;

	@Autowired
	public GameService(ProcessFile log) {
		this.gameList = new ArrayList<>();
		this.log = log;
	}

	public List<Game> getGameList() {

		if(gameList.isEmpty()) {
			readLog();
		}

		return gameList;
	}
	
	public Game getGameByNumber(int gameNumber) {
		
		if(gameList.isEmpty()) {
			readLog();
		}
		
		return gameList.stream().filter(game -> game.getNumber() == gameNumber).findFirst().orElse(null);
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
		actualGame = new Game(gameNumber);
		gameNumber++;
	}

}
