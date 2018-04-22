package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.config.GameSetup;
import com.api.model.Game;
import com.api.util.NumberAwareStringComparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QuakeService {

	private GameSetup gameSetup;

	private static final String WORLD_PLAYER_ID = "1022";

	private static int gameNumber = 0;

	private Game currentGame;

	private Map<String, String> currentPlayers;

	private Map<String, Integer> currentKills;

	private Map<String, Game> gameMap;

	@Autowired
	public QuakeService(GameSetup gameSetup) {
		this.currentGame = null;
		this.gameSetup = gameSetup;
		this.gameMap = new TreeMap<>(new NumberAwareStringComparator());
	}

	public Map<String, Game> getGames() {

		if (gameMap.isEmpty()) {
			parseFile();
		}

		return gameMap;

	}

	public Object getGame(int number) {

		if (gameMap.isEmpty()) {
			parseFile();
		}

		return gameMap.values().stream().filter(g -> ((Game) g).getNumber() == number).findFirst().orElse(null);
	}

	private void parseFile() {

		for (String line : gameSetup.getLines()) {

			if (line.indexOf("InitGame") != -1) {
				parseInitGame(line.trim());
				continue;
			}

			if (line.indexOf("killed") != -1) {
				parseKilled(line.trim());
				continue;
			}

			if (line.indexOf("ClientUserinfoChanged") != -1) {
				parseClientUserinfoChanged(line.trim());
				continue;
			}

		}

	}

	private void parseClientUserinfoChanged(String line) {

		String[] words = line.split("\\s+");

		String playerID = words[2];

		String playerName = getNameOfArray(line);

		Boolean nameExists = currentPlayers.containsValue(playerName);
		Boolean idExists = currentPlayers.containsKey(playerID);

		if (!nameExists && !idExists) {
			//	NOVO PLAYER
			currentPlayers.put(playerID, playerName);
			currentKills.put(playerName, 0);

		}else if(!nameExists && idExists) {
			// PLAYER TROCOU DE NOME
			int playerKills = currentKills.get(currentPlayers.get(playerID));			
			currentKills.remove(currentPlayers.get(playerID));
			
			currentPlayers.put(playerID, playerName);
			currentKills.put(playerName, playerKills);
			
		}else if (nameExists && idExists) {
			
			String oldPlayerReconnectedKey = getPlayerKeyByName(playerName);
			// PLAYER RECONECTOU COM ID EXISTENTE
			if (!oldPlayerReconnectedKey.equals(playerID)) {

				String newPlayerName = currentPlayers.get(playerID);

				currentPlayers.remove(oldPlayerReconnectedKey);
				currentPlayers.remove(playerID);

				currentPlayers.put(playerID, playerName);
				currentPlayers.put(oldPlayerReconnectedKey, newPlayerName);
			}
		}else if(nameExists && !idExists) {
			// PLAYER RECONECTOU COM ID NOVO
			currentPlayers.remove(getPlayerKeyByName(playerName));
			currentPlayers.put(playerID, playerName);
		}
		currentGame.setKills(currentKills);
		currentGame.setPlayers(currentPlayers.values().toArray());

	}

	private void parseKilled(String line) {

		int totalKills = currentGame.getTotalKills();
		currentGame.setTotalKills(++totalKills);

		String[] words = line.split(" +");

		String playerKiller = currentPlayers.get(words[2]);
		String playerKilled = currentPlayers.get(words[3]);

		if (!words[2].equals(WORLD_PLAYER_ID)) {
			
			int playerKillerKills = currentKills.get(playerKiller);
			currentKills.put(playerKiller, ++playerKillerKills);
		} else {
			
			int playerKilledKills = currentKills.get(playerKilled);
			currentKills.put(playerKilled, --playerKilledKills);
		}

		currentGame.setKills(currentKills);

	}

	private void parseInitGame(String line) {

		gameNumber++;

		currentPlayers = new TreeMap<>();
		currentKills = new TreeMap<>();

		Game game = new Game();
		game.setTotalKills(0);
		game.setPlayers(currentPlayers.values().toArray());
		game.setNumber(gameNumber);
		game.setKills(currentKills);

		gameMap.put("game_" + gameNumber, game);

		currentGame = game;

	}

	private String getNameOfArray(String str) {

		Pattern pattern = Pattern.compile("(n\\\\)(.+?)(\\\\t)");
		Matcher m = pattern.matcher(str);
		List<String> l = new ArrayList<String>();
		while (m.find()) {
			String s = m.group();
			s = s.substring(1);
			l.add(s);
		}
		return l.toArray(new String[0])[0].split("\\\\")[1];
	}

	// private Player getPlayerById(String playerID) {
	// return players.stream().filter(p ->
	// p.getId().equals(playerID)).findFirst().orElse(null);
	// }
	//
	// private Player getPlayerByName(String playerName) {
	// return players.stream().filter(p ->
	// p.getName().equals(playerName)).findFirst().orElse(null);
	// }

	private String getPlayerKeyByName(String playerName) {
		return currentPlayers.entrySet().stream().filter(e -> e.getValue().equals(playerName)).findFirst().get()
				.getKey();
	}

}
