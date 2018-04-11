package com.luizalabs.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode
public class Game {

	private static final int WORLD_PLAYER_ID = 1022;

	private static int gameNumber = 1;

	private int totalKills;

	// private final Map<Integer, String> playerMap;

	private List<Player> players;

	private final Map<Integer, Integer> kills;

	@JsonIgnore
	private boolean initiated = false;
	@JsonIgnore
	private boolean finished = false;

	public Game() {
		totalKills = 0;
		players = new ArrayList<>();
		kills = new HashMap<>();

	}

	public void addEvent(Row row) {
		
		Player player;
		
		switch (row.getEvent()) {
		case Kill:

			DeathMeaning deathMeaning = DeathMeaning.valueOf(Integer.parseInt(row.getTarget()));
			int killerID = Integer.parseInt(row.getDescription());
			int killedID = Integer.parseInt(row.getOrigin());

			// addKill(killerID, killedID, deathMeaning);
			addKill(killerID, killedID);
			break;
		case ClientConnect:

			int playerID = Integer.parseInt(row.getDescription());

			// playerMap.put(playerID, null);
			player = players.stream().filter(p -> p.getId() == playerID).findFirst().orElse(null);
			if (player != null) {
				players.set(players.indexOf(player), player);
			}else {
				registerPlayer(playerID, "");
			}
			// kills.put(playerID, 0);

			break;
		case ClientUserinfoChanged:

			playerID = Integer.parseInt(row.getDescription());
			String playerName = row.getTarget();
			
			player = players.stream().filter(p -> p.getId() == playerID).findFirst().orElse(null);
			if (player != null) {
				players.set(players.indexOf(player), player);
			}else {
				registerPlayer(playerID, playerName);
			}

			// playerMap.put(playerID, playerName);
			// registerPlayer(playerID, playerName);
			break;
		case ClientDisconnect:
			playerID = Integer.parseInt(row.getDescription());
			// playerMap.remove(playerID);
			break;
		case ShutdownGame:

			finished = true;
			break;
		case InitGame:

			// We should get here only once per game
			if (initiated && !finished) {
				// We are getting a second InitGame without having finished the
				// actual game first. It may indicated that we missed some line
				// of the log.
				finished = true;

				// NOTICE We could throw an exception here
			}
			initiated = true;

			break;
		}
	}

	void addKill(int killerID, int killedID) {

		if (killerID != WORLD_PLAYER_ID) {
			// int playerTotalKills = kills.get(killerID);
			// kills.put(killerID, ++playerTotalKills);

			Player player = players.stream().filter(p -> p.getId() == killerID).findFirst().get();

			int playerTotalKills = player.getKills();
			player.setKills(playerTotalKills++);
			players.set(players.indexOf(player), player);

		}

		totalKills++;
	}

	/*void addKill(int killerID, int killedID, DeathMeaning death) {
		addKill(killerID, killedID);
	}*/

//	private String[] getPlayers() {
//
////		return players.stream().
//		
//	}

	public String[] getKills() {

		List<String> result = new ArrayList<>();

		// for (Integer i : players.keySet()) {
		// String player = playerMap.get(i);
		// int playerKills = kills.get(i);
		// result.add("\"" + player + "\": " + playerKills);
		// }

		for (Player player : players) {
			String playerName = player.getName();
			int playerKills = player.getKills();
			result.add("\"" + playerName + "\": " + playerKills);
		}

		return result.toArray(new String[] {});
	}

	void registerPlayer(int id, String playerName) {

		if (id == WORLD_PLAYER_ID) {
			return;
		}

		// if (playerMap.containsKey(id)) {
		// playerMap.put(id, playerName);
		// kills.put(id, 0);
		// }

		players.add(new Player(id, playerName, 0));

	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append("\t{\n");
		result.append("game_").append(gameNumber).append(": {\n");
		result.append("\ttotal_kills: ").append(this.getTotalKills()).append(",\n");

		if(this.getPlayers() == null) {
			System.out.println("BREAK!");
		}
		
		// Players
		result.append("\tplayers: [");
//		for (String playerName : this.getPlayers()) {
//			result.append('"').append(playerName).append('"').append(',');
//		}
		result.deleteCharAt(result.lastIndexOf(","));
		result.append("]\n").append(',');
		result.append("\tkills: {\n");

		// Kills
		for (String killLine : this.getKills()) {
			result.append("\t\t").append(killLine).append(",\n");
		}
		result.deleteCharAt(result.lastIndexOf(","));
		result.append("\t}\n").append("\t}\n").append(',');
		gameNumber++;

		return result.toString();
	}

}
