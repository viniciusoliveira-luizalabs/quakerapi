package com.luizalabs.readers;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 *         Leitor que se baseia na linha lida no log para armazenar informações
 *
 */
public class ScoreReader extends GenericReader {

	@Override
	public Row processLine(Row row) {
		if (row.getEvent().equals(Event.score)) {
			String rawLine = row.getLine();

			String[] split = rawLine.split(" +");

			String score = split[2];
			String ping = split[4];
			String playerID = split[6];

			row.setDescription(playerID);
			row.setOrigin(score);
			row.setTarget(ping);

			return row;

		} else if (successor != null) {
			return successor.processLine(row);
		} else {
			return Row.blankEvent();
		}
	}

}
