package com.api.model;

import java.util.Map;

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

	private Object[] players;
	
	private Map<String, Integer> kills;
	
	
}
