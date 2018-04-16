package com.luizalabs.readers;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 *         Leitor que se baseia na linha lida no log para armazenar informações
 *
 */
public class ExitReader extends GenericReader {

	@Override
	public Row processLine(Row row) {
		if (row.getEvent().equals(Event.Exit)) {
			String rawLine = row.getLine();

			String[] split = rawLine.split("\\s+", 3);

			row.setDescription(split[2]);

			return row;
		} else if (successor != null) {
			return successor.processLine(row);
		} else {
			return Row.blankEvent();
		}
	}

}
