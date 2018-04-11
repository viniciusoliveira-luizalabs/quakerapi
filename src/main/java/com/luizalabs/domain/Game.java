package com.luizalabs.domain;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class Game {

	private static long number = 0;
	private static long totalKills = 0;
	private List<Player> players;

	public Game(List<Player> players) {
		this.players = new ArrayList<Player>(players);
	}
}
