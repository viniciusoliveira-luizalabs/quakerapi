package com.luizalabs.readers;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
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
