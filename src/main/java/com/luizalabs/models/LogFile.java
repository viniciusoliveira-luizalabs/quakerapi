package com.luizalabs.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.stream.Stream;

public class LogFile implements Iterable<String> {

	private Stream<String> lines;

	public LogFile(File file) {

		try {
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			
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
