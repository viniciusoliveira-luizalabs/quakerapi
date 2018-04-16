package com.luizalabs.resource;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.service.GameService;

/**
 * @author Ivo
 * 
 * Controller do jogo, com endpoints para acesso a api
 *
 */
@RestController
@RequestMapping(path = "luizalabs/")
public class GameResource {

	@Autowired
	private GameService gameService;

	@GetMapping(path = "games", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getGames() throws JSONException {

		return new ResponseEntity<Object>(gameService.getGameList(), HttpStatus.OK);
	}
	
	@GetMapping(path = "game/{number}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getGame(@PathVariable int number) throws JSONException {

		return new ResponseEntity<Object>(gameService.getGameByNumber(number), HttpStatus.OK);
	}
}
