package com.luizalabs.readers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public class InfoChangedReader extends GenericReader {

    @Override
    public Row processLine(Row row) {
		if (row.getEvent().equals(Event.ClientUserinfoChanged)) {
            String rawrow = row.getLine();

            String[] split = rawrow.split("\\s+");

            row.setDescription(split[2]);            
            
            Pattern pattern = Pattern.compile("(n\\\\)(.+?)(\\\\t)");
            
            String target = getArray(pattern, rawrow)[0].split("\\\\")[1];
  
            row.setTarget(target);

            return row;
        } else if (successor != null) {
            return successor.processLine(row);
        } else {
            return Row.blankEvent();
        }
    }
    
    private String[] getArray(Pattern tagMatcher, String str) {
        Matcher m = tagMatcher.matcher(str);
        List<String> l = new ArrayList<String>();
        while(m.find()) {
            String s = m.group(); 
            s = s.substring(1); 
            l.add(s);
        }
        return l.toArray(new String[0]);
    }

}
