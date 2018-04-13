package com.luizalabs.readers;

import com.luizalabs.models.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class ConnectReader extends GenericReader {

    @Override
    public Row processLine(Row row) {
        if (row.getEvent().equals(Event.ClientConnect)) {
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
