package com.luizalabs.service;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Date;
import com.luizalabs.models.Row;
import com.luizalabs.readers.BeginReader;
import com.luizalabs.readers.ConnectReader;
import com.luizalabs.readers.DisconnectReader;
import com.luizalabs.readers.ExitReader;
import com.luizalabs.readers.InfoChangedReader;
import com.luizalabs.readers.InitReader;
import com.luizalabs.readers.ItemReader;
import com.luizalabs.readers.KillReader;
import com.luizalabs.readers.SayReader;
import com.luizalabs.readers.ScoreReader;
import com.luizalabs.readers.ShutdownReader;

/**
 * @author Ivo
 * 
 *         Define parametros para sucessao de eventos do jogo
 *
 */
public class GameSetup {

	public static Row readLine(String line) {

		if (!checkLine(line)) {
			return Row.blankEvent();
		}

		Row partialLine = read(line);

		BeginReader beginReader = new BeginReader();
		ConnectReader connectReader = new ConnectReader();
		beginReader.setSuccessor(connectReader);

		DisconnectReader disconnectReader = new DisconnectReader();
		connectReader.setSuccessor(disconnectReader);

		InfoChangedReader infoChangedReader = new InfoChangedReader();
		disconnectReader.setSuccessor(infoChangedReader);

		InitReader initReader = new InitReader();
		infoChangedReader.setSuccessor(initReader);

		ShutdownReader shutdownGameReader = new ShutdownReader();
		initReader.setSuccessor(shutdownGameReader);

		ExitReader exitReader = new ExitReader();
		shutdownGameReader.setSuccessor(exitReader);

		ItemReader itemReader = new ItemReader();
		exitReader.setSuccessor(itemReader);

		KillReader killReader = new KillReader();
		itemReader.setSuccessor(killReader);

		ScoreReader scoreReader = new ScoreReader();
		killReader.setSuccessor(scoreReader);

		SayReader sayReader = new SayReader();
		scoreReader.setSuccessor(sayReader);

		return beginReader.processLine(partialLine);

	}

	static Row read(String line) {

		String[] split = line.trim().split(" +");

		String[] time = split[0].split(":");

		String[] reason = new String[2];
		if (!split[1].matches("\\w+:")) {
			reason[0] = "BLANK";
		} else {
			reason = split[1].split(":", 2);
		}

		Row logLine = new Row(new Date(Integer.parseInt(time[0]), Integer.parseInt(time[1])), Event.valueOf(reason[0]));

		logLine.setLine(line);

		return logLine;
	}

	private static boolean checkLine(String line) {
		return line.matches("^\\s*?\\d+:\\d+ \\w+:.*$");
	}

}
