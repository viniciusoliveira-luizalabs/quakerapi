package com.luizalabs.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	private static int gameNumber = 0;

	private int totalKills;

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

		switch (row.getEvent()) {
		case Kill:

			int killerID = Integer.parseInt(row.getDescription());
			int killedID = Integer.parseInt(row.getOrigin());

			addKill(killerID, killedID);
			break;
		case ClientConnect:

			int playerID = Integer.parseInt(row.getDescription());

			registerPlayer(playerID);

			break;
		case ClientUserinfoChanged:

			playerID = Integer.parseInt(row.getDescription());
			String playerName = row.getTarget();
			
			// verifica se existe um jogador na lista com o nome passado
			Player player = getPlayerByName(playerName);
			if (player != null) {
				// pega a chave do jogador existente
				int oldPlayerReconnectedKey = player.getId();
				// verifica se o jogador reconectou com diferente ID
				if (oldPlayerReconnectedKey != playerID) {
					// prepara jogador a ser trocado
					Player playerSwap = players.stream().filter(p -> p.getId() == playerID).findFirst().orElse(null);
					// troca os valores de id dos jogadores
					playerSwap.setId(oldPlayerReconnectedKey);
					player.setId(playerID);
					// atualiza chave e valor no objeto kills
					int killsAux = kills.get(playerID);
					kills.put(playerID, kills.get(oldPlayerReconnectedKey) + killsAux);
					kills.put(oldPlayerReconnectedKey, killsAux);
				}
			} else {
				player = getPlayerById(playerID);
				player.setName(playerName);
				players.set(players.indexOf(player), player);
			}

			break;
		case ClientDisconnect:
			playerID = Integer.parseInt(row.getDescription());
			break;
		case ShutdownGame:
			removeEmptyNamedPlayers();
			finished = true;
			break;
		case InitGame:
			if (initiated && !finished) {
				removeEmptyNamedPlayers();
				finished = true;
			}
			initiated = true;

			break;
		}
	}
	
	private Player getPlayerById(int playerID) {
		return players.stream().filter(p -> p.getId() == playerID).findFirst().orElse(null);
	}
	
	private Player getPlayerByName(String playerName) {
		return players.stream().filter(p -> p.getName().equals(playerName)).findFirst().orElse(null);
	}

	void addKill(int killerID, int killedID) {

		if (killerID != WORLD_PLAYER_ID) {
				int playerTotalKills = kills.get(killerID);
				kills.put(killerID, ++playerTotalKills);
		} else {
			int playerTotalKills = kills.get(killedID);
			kills.put(killedID, --playerTotalKills);
		}

		totalKills++;
	}


	void registerPlayer(int playerId) {

		if (playerId == WORLD_PLAYER_ID) {
			return;
		}

		players.add(new Player(playerId, ""));
		kills.put(playerId, 0);

	}
	
	private void removeEmptyNamedPlayers() {
		List<Player> emptyNamePlayers = players.stream().filter(p -> p.getName().isEmpty())
				.collect(Collectors.toList());

		players.removeAll(emptyNamePlayers);
	}

	@Override
	public String toString() {
		return "Game [totalKills=" + totalKills + ", players=" + players + ", kills=" + kills + "]";
	}

}
