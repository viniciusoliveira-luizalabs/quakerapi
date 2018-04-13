package com.luizalabs.resource;

import java.io.File;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.models.LogFile;
import com.luizalabs.service.GameService;

/**
 * @author Ivo
 *
 */
@RestController
@RequestMapping(path = "luizalabs/")
public class GameResource{

	@GetMapping(path = "game-info", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getGamesInfo() throws JSONException {

		LogFile log = new LogFile(new File("src/main/java/games.log"));
		GameService parser = new GameService(log);

		return new ResponseEntity<Object>(parser.getGameList(), HttpStatus.OK);
	}
}
