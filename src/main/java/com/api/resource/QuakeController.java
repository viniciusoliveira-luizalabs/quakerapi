package com.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.QuakeService;
/**
 * @author ivofreitas
 * Classe controller
 */
@RestController
@RequestMapping(path = "api/v1/")
public class QuakeController {

	@Autowired
	private QuakeService quakeService;

	@GetMapping(path = "games", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getGames() {

		return new ResponseEntity<Object>(quakeService.getGames(), HttpStatus.OK);
	}

	@GetMapping(path = "game/{number}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getGame(@PathVariable int number) {

		return new ResponseEntity<Object>(quakeService.getGame(number), HttpStatus.OK);
	}

}
