package com.luizalabs.resource;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.models.Game;
import com.luizalabs.models.LogFile;
import com.luizalabs.parsers.QuakeParser;
import com.luizalabs.util.BaseResource;

@RestController
@RequestMapping(path = "luizalabs/")
public class GameResource extends BaseResource{
	
	@GetMapping(path = "game-info")
	public String/*ResponseEntity<?>*/ getGamesInfo() throws JSONException {
		
		LogFile log = new LogFile(new File("src/main/java/games.log"));
        QuakeParser parser = new QuakeParser(log);

        List<Game> gameList = parser.getGameList();
		
//		return buildResponse(HttpStatus.OK, Optional.ofNullable(parser.results()));
        return parser.results();
	}
}
