package com.luizalabs.service;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizalabs.models.Player;
import com.luizalabs.models.Row;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

/**
 * @author Ivo
 *
 *         Objeto para manipulacao de informacoes do jogo
 */
@Getter
@Setter
public class Game {
	
	private int number = 0;

	private static final int WORLD_PLAYER_ID = 1022;

	private int totalKills;

	private List<Player> players;

	@JsonIgnore
	private boolean initiated = false;
	@JsonIgnore
	private boolean finished = false;


	public Game(int number) {
		this.number = number;
		totalKills = 0;
		players = new ArrayList<>();

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

			if (getPlayerById(playerID) == null)
				registerPlayer(playerID);

			break;
		case ClientUserinfoChanged:

			playerID = Integer.parseInt(row.getDescription());
			String playerName = row.getTarget();

			Player player = getPlayerByName(playerName);
			if (player != null) {
				int oldPlayerReconnectedKey = player.getId();
				if (oldPlayerReconnectedKey != playerID) {

					Player playerOld = player;
					Player playerNew = getPlayerById(playerID);

					playerNew.setKills(playerOld.getKills() + playerNew.getKills());
					playerOld.setKills(playerNew.getKills());

					playerNew.setId(oldPlayerReconnectedKey);
					playerOld.setId(playerID);

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
			finished = true;
			break;
		case InitGame:
			if (initiated && !finished) {
				finished = true;
			}
			initiated = true;

			break;
		default:
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

			Player player = getPlayerById(killerID);
			player.setKills(player.getKills() + 1);

		} else {

			Player player = getPlayerById(killedID);
			player.setKills(player.getKills() - 1);
		}

		totalKills++;

	}

	void registerPlayer(int playerId) {

		if (playerId == WORLD_PLAYER_ID) {
			return;
		}

		players.add(new Player(playerId, "", 0));

	}
	
	public int getNumber() {
		return number;
	}

}
