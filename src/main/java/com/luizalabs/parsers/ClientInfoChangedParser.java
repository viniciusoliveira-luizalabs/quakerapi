package com.luizalabs.parsers;

import com.luizalabs.models.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class ClientInfoChangedParser extends AbstractLineParser {

    @Override
    public Row processLine(Row row) {
		if (row.getEvent().equals(Event.ClientUserinfoChanged)) {
            String rawrow = row.getRawLine();

            String[] split = rawrow.split("\\s+");

            row.setDescription(split[2]);

            String info = split[3];

            String[] details = info.split("\\\\");

            for (int i = 0; i < details.length; i++) {
                String detail = details[i];

                switch (detail) {
                    case "n":
                        row.setTarget(details[i + 1]);
                        i++;
                        break;
                }

            }

            return row;
        } else if (successor != null) {
            return successor.processLine(row);
        } else {
            return Row.blankEvent();
        }
    }

}
