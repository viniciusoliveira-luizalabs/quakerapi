package com.api.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * @author ivofreitas
 * 
 * Classe para leitura de arquivo
 */
@Component
public class GameSetup {

	Logger logger = LoggerFactory.getLogger(GameSetup.class);

	private Stream<String> linesStream;

	private String[] lines;

	public GameSetup() {
		URL resource = GameSetup.class.getClassLoader().getResource("games.log");

		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader(Paths.get(resource.toURI()).toFile()));

			linesStream = bufferedReader.lines();

			lines = linesStream.toArray(size -> new String[size]);

		} catch (FileNotFoundException | URISyntaxException e) {
			logger.info(e.getMessage());

		} finally {

			try {

				if (bufferedReader != null) {
					bufferedReader.close();
				}

			} catch (IOException e) {
				logger.info(e.getMessage());
			}

		}

	}

	public String[] getLines() {
		return lines;
	}

}
