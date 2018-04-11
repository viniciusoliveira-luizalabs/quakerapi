package com.luizalabs.parsers;

import com.luizalabs.models.Date;
import com.luizalabs.models.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class LineParser {

	public static Row parseLine(String line) {

		if (!_validateLine(line)) {
			return Row.blankEvent();
		}

		Row partialLine = LineParser.basicParse(line);

		ClientBeginParser beginParser = new ClientBeginParser();
		ClientConnectParser clientConnectParser = new ClientConnectParser();
		ClientDisconnectParser clientDisconnectParser = new ClientDisconnectParser();
		ClientInfoChangedParser clientInfoChangedParser = new ClientInfoChangedParser();
		InitGameParser initGameParser = new InitGameParser();
		ShutdownGameParser shutdownGameParser = new ShutdownGameParser();
		ExitParser exitParser = new ExitParser();
		ItemParser itemParser = new ItemParser();
		KillParser killParser = new KillParser();
		ScoreParser scoreParser = new ScoreParser();
		SayParser sayParser = new SayParser();

		beginParser.setSuccessor(clientConnectParser);
		clientConnectParser.setSuccessor(clientDisconnectParser);
		clientDisconnectParser.setSuccessor(clientInfoChangedParser);
		clientInfoChangedParser.setSuccessor(initGameParser);
		initGameParser.setSuccessor(shutdownGameParser);
		shutdownGameParser.setSuccessor(exitParser);
		exitParser.setSuccessor(itemParser);
		itemParser.setSuccessor(killParser);
		killParser.setSuccessor(scoreParser);
		scoreParser.setSuccessor(sayParser);

		return beginParser.processLine(partialLine);

	}

	static Row basicParse(String line) {

		String[] split = line.trim().split(" +");

		String[] time = split[0].split(":");

		String[] reason = new String[2];
		if (!split[1].matches("\\w+:")) {
			reason[0] = "BLANK";
		} else {
			reason = split[1].split(":", 2);
		}

		Row logLine = new Row(new Date(Integer.parseInt(time[0]), Integer.parseInt(time[1])), Event.valueOf(reason[0]));

		logLine.setRawLine(line);

		return logLine;
	}

	private static boolean _validateLine(String line) {
		return line.matches("^\\s*?\\d+:\\d+ \\w+:.*$");
	}

}
