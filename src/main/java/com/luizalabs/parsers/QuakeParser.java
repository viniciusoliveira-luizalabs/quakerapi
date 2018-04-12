package com.luizalabs.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.luizalabs.models.DeathMeaning;
import com.luizalabs.models.Game;
import com.luizalabs.models.LogFile;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class QuakeParser {

	private boolean parsed = false;

	private final LogFile log;
	private Game actualGame;

	private final List<Game> gameList;

	public QuakeParser(LogFile log) {
		this.gameList = new ArrayList<>();
		this.log = log;
	}

	public List<Game> getGameList() {

		if (!this.parsed) {
			parse();
		}

		return gameList;
	}

	private void parse() {

		configureNewGame();

		for (String rawLine : log) {
			Row line = LineParser.parseLine(rawLine);

			actualGame.addEvent(line);

			if (actualGame.isFinished()) {
				configureNewGame();
			}

		}

		parsed = true;

	}

	private void configureNewGame() {
		if (actualGame != null) {
			gameList.add(actualGame);
		}
		actualGame = new Game();
	}

	public String results() throws JSONException {

		if (!this.parsed) {
			parse();
		}

		StringBuilder result = new StringBuilder();
		result.append("\t{\n");
		// int gameCount = 1;
		for (Iterator<Game> it = gameList.iterator(); it.hasNext();) {
			Game game = (Game) it.next();

			result.append(game.toString());

			/*
			 * //Total kills result.append("game_").append(gameCount).append(": {\n");
			 * result.append("\ttotal_kills: ").append(game.getTotalKills()).append(";\n");
			 * 
			 * //Players result.append("\tplayers: ["); for (String playerName :
			 * game.getPlayers()) {
			 * result.append('"').append(playerName).append('"').append(','); }
			 * result.deleteCharAt(result.lastIndexOf(",")); result.append("]\n");
			 * result.append("\tkills: {\n");
			 * 
			 * //Kills for (String killLine : game.getKills()) {
			 * result.append("\t\t").append(killLine).append(",\n"); }
			 * result.deleteCharAt(result.lastIndexOf(",")); result.append("\t}\n");
			 * 
			 * result.append("\t}\n");
			 * 
			 * gameCount++;
			 */

		}
		result.deleteCharAt(result.lastIndexOf(","));
		result.append("\t}\n");

		return result.toString();
//		 return new JSONObject(result.toString());
	}

}
