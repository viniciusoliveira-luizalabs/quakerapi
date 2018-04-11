package com.luizalabs.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LineParser extends GenericParser {

	public void read(File file) {

		Stream<String> lines;

		Map<String, Long> wordsMap = new HashMap<String, Long>();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			lines = bufferedReader.lines();

			lines.forEach(line -> {
				List<String> words = Arrays.asList(line.split("\\s+"));
				
				words.forEach(word -> {
					wordsMap.computeIfPresent(word, (k, v) -> v + 1);
				});
				
			});
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//return lines;
	}

}
