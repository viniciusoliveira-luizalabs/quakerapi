package com.api.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

/**
 * @author Ivo
 * 
 *         Classe para leitura do log
 *
 */
@Component
public class ProcessFile implements Iterable<String> {

	private Stream<String> lines;

	public ProcessFile() {
		readFile();
	}

	private void readFile() {

		try {
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/main/java/games.log")));

			lines = bufferedReader.lines();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Iterator<String> iterator() {
		return this.lines.iterator();
	}

}
