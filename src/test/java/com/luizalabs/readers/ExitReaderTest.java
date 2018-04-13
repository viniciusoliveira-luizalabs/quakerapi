package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Date;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class ExitReaderTest {

	private GenericReader reader = new ExitReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.Exit);
		
		row.setLine("15:00 Exit: Timelimit hit.");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
