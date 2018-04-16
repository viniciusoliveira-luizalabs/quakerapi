package com.api.readers;

import com.api.enums.Event;
import com.api.models.Row;

/**
 * @author Ivo
 *
 *         Leitor que se baseia na linha lida no log para armazenar informações
 *
 */
public class ShutdownReader extends GenericReader {

	@Override
	public Row processLine(Row row) {
		if (row.getEvent().equals(Event.ShutdownGame)) {
			return row;
		} else if (successor != null) {
			return successor.processLine(row);
		} else {
			return Row.blankEvent();
		}
	}

}
