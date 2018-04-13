package com.luizalabs.readers;

import com.luizalabs.models.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class SayReader extends GenericReader {

    @Override
    public Row processLine(Row row) {
        if (row.getEvent().equals(Event.say)) {
            String rawLine = row.getLine();

            String[] split = rawLine.split(" +", 3);

            String[] contents = split[2].split(":");

            String subject = contents[0];
            String indirectObject = contents[1].trim();

            row.setDescription(subject);
            row.setTarget(indirectObject);

            return row;

        } else if (successor != null) {
            return successor.processLine(row);
        } else {
        	return Row.blankEvent();
        }
    }

}