package com.luizalabs.parsers;

import com.luizalabs.models.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class ScoreParser extends AbstractLineParser {

    @Override
    public Row processLine(Row row) {
        if (row.getEvent().equals(Event.score)) {
            String rawLine = row.getRawLine();

            String[] split = rawLine.split(" +");

            String score = split[2];
            String ping = split[4];
            String playerID = split[6];

            row.setDescription(playerID);
            row.setOrigin(score);
            row.setTarget(ping);

            return row;

        } else if (successor != null) {
            return successor.processLine(row);
        } else {
        	return Row.blankEvent();
        }
    }

}
