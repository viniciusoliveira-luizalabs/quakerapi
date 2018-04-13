package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Date;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class ShutdownReaderTest {

	private GenericReader reader = new ShutdownReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ShutdownGame);
		
		row.setLine("12:13 ShutdownGame:");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
