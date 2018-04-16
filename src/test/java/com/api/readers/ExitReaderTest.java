package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.ExitReader;
import com.api.readers.GenericReader;

public class ExitReaderTest {

	private GenericReader reader = new ExitReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.Exit);
		
		row.setLine("15:00 Exit: Timelimit hit.");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
