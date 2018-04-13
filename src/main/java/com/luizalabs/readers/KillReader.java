package com.luizalabs.readers;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
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
