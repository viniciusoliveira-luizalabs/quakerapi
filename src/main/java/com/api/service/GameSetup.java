package com.api.service;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.BeginReader;
import com.api.readers.ConnectReader;
import com.api.readers.DisconnectReader;
import com.api.readers.ExitReader;
import com.api.readers.InfoChangedReader;
import com.api.readers.InitReader;
import com.api.readers.ItemReader;
import com.api.readers.KillReader;
import com.api.readers.SayReader;
import com.api.readers.ScoreReader;
import com.api.readers.ShutdownReader;

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
