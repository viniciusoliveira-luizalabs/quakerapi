package com.luizalabs.parsers;

import com.luizalabs.models.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class ShutdownGameParser extends AbstractLineParser {

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
