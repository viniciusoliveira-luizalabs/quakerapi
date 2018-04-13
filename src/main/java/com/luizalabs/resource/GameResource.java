package com.luizalabs.resource;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.service.GameService;

/**
 * @author Ivo
 *
 */
@RestController
@RequestMapping(path = "luizalabs/")
public class GameResource {

	@Autowired
	private GameService gameService;

	@GetMapping(path = "game-info", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getGamesInfo() throws JSONException {

		return new ResponseEntity<Object>(gameService.getGameList(), HttpStatus.OK);
	}
}
