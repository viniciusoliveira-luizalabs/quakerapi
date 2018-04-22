package com.api.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author ivofreitas
 * 
 * Objeto que armazena informações do jogo
 */
@Data
@NoArgsConstructor
@ToString
public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private int number = 0;

	private int totalKills;

	private Object[] players;
	
	private Map<String, Integer> kills;
	
	
}
