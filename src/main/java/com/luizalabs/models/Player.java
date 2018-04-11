package com.luizalabs.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	
	private int id;
	private String name;
	private int kills;
	
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
