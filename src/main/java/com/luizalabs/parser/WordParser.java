package com.luizalabs.parser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordParser extends GenericParser {

	public Map<String, Long> read(String line) {

		String[] words = line.split("\\s+");
		Map<String, Long> wordsMap = new HashMap<String, Long>();

		for (String word : words) {
			wordsMap.computeIfPresent(word, (k, v) -> v + 1);
		}

	}

}
