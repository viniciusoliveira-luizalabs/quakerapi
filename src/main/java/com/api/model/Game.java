package com.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Game {

	@JsonIgnore
	private int number = 0;

	private int totalKills;

	private List<Player> players;

	public boolean addPlayer(Player player) {
		return players.add(player);
	}

	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	public void updatePlayer(Player player) {
		players.set(players.indexOf(player), player);
	}

	public Player getPlayerById(String playerID) {
		return players.stream().filter(p -> p.getId().equals(playerID)).findFirst().orElse(null);
	}

	public Player getPlayerByName(String playerName) {
		return players.stream().filter(p -> p.getName().equals(playerName)).findFirst().orElse(null);
	}
}
