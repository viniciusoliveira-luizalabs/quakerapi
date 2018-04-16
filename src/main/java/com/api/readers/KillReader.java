package com.api.readers;

import com.api.enums.Event;
import com.api.models.Row;

/**
 * @author Ivo
 *
 *         Leitor que se baseia na linha lida no log para armazenar informações
 *
 */
public class KillReader extends GenericReader {

	@Override
	public Row processLine(Row row) {
		if (row.getEvent().equals(Event.Kill)) {
			String rawLine = row.getLine().trim();

			String[] split = rawLine.split(" +");

			row.setDescription(split[2]);
			row.setOrigin(split[3]);
			row.setTarget(split[4].split(":", 2)[0]);

			return row;

		} else if (successor != null) {
			return successor.processLine(row);
		} else {
			return Row.blankEvent();
		}
	}

}
