package com.luizalabs.parsers;

import com.luizalabs.models.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class ExitParser extends AbstractLineParser {

    @Override
    public Row processLine(Row row) {
        if (row.getEvent().equals(Event.Exit)) {
            String rawLine = row.getRawLine();

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
