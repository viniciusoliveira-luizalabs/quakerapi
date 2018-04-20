package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.config.GameSetup;
import com.api.model.Game;
import com.api.model.Player;
import com.api.util.NumberAwareStringComparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QuakeService {

	private GameSetup gameSetup;

	private static final String WORLD_PLAYER_ID = "1022";

	private static int gameNumber = 0;

	private Game currentGame;

	private Map<String, Game> gameMap;
	

	@Autowired
	public QuakeService(GameSetup gameSetup) {
		this.currentGame = null;
		this.gameSetup = gameSetup;
		this.gameMap = new TreeMap<>(new NumberAwareStringComparator());;
	}

	public Map<String, Game> getGames() {

		if (gameMap.isEmpty()) {
			parseFile();
		}

		return gameMap;

	}

	public Game getGame(int number) {

		if (gameMap.isEmpty()) {
			parseFile();
		}

		// return games.stream().filter(g -> g.getNumber() ==
		// number).findFirst().orElseThrow(new IllegalArgumentException("Jogo não
		// encontrado!"));
		return gameMap.values().stream().filter(g -> g.getNumber() == number).findFirst().orElse(null);
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

		Player player = currentGame.getPlayerByName(playerName);
		
		if (player == null) {
			player = currentGame.getPlayerById(playerID);
			if (player == null) {
				player = new Player();
				player.setId(playerID);
				player.setName(playerName);
				currentGame.addPlayer(player);
			}else {
				player.setName(playerName);
				currentGame.updatePlayer(player);
			}			
		}else if (!player.getId().equals(playerID)) {

			String oldPlayerReconnectedKey = player.getId();
			if (!oldPlayerReconnectedKey.equals(playerID)) {

				Player playerOld = player;
				Player playerNew = currentGame.getPlayerById(playerID);

				if (playerNew != null) {

					playerNew.setId(oldPlayerReconnectedKey);
					playerOld.setId(playerID);

					currentGame.updatePlayer(playerOld);
					currentGame.updatePlayer(playerNew);
				}
				else {
					playerOld.setId(playerID);
					currentGame.updatePlayer(playerOld);
				}
			}
		}

	}

	private void parseKilled(String line) {

		int totalKills = currentGame.getTotalKills();
		currentGame.setTotalKills(++totalKills);

		String[] words = line.split(" +");

		Player playerKiller = currentGame.getPlayerById(words[2]);
		Player playerKilled = currentGame.getPlayerById(words[3]);

		if (!words[2].equals(WORLD_PLAYER_ID)) {
			int playerKillerkills = playerKiller.getKills();
			playerKiller.setKills(++playerKillerkills);
		} else {
			int playerKilledkills = playerKilled.getKills();
			playerKilled.setKills(--playerKilledkills);
		}

	}

	private void parseInitGame(String line) {

		gameNumber++;

		Game game = new Game();
		game.setTotalKills(0);
		game.setPlayers(new ArrayList<>());
		game.setNumber(gameNumber);
		
		gameMap.put("game_"+gameNumber, game);

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

}
