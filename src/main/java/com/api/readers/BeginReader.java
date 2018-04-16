package com.api.readers;

import com.api.enums.Event;
import com.api.models.Row;

/**
 * @author Ivo
 *
 *         Leitor que se baseia na linha lida no log para armazenar informações
 *
 */
public class BeginReader extends GenericReader {

	@Override
	public Row processLine(Row row) {
		if (row.getEvent().equals(Event.ClientBegin)) {
			String rawLine = row.getLine();

			String[] split = rawLine.split("\\s+");

			row.setDescription(split[2]);

			return row;
		} else if (successor != null) {
			return successor.processLine(row);
		} else {
			return Row.blankEvent();
		}
	}

}
