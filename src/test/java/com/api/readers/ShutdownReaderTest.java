package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.GenericReader;
import com.api.readers.ShutdownReader;

public class ShutdownReaderTest {

	private GenericReader reader = new ShutdownReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ShutdownGame);
		
		row.setLine("12:13 ShutdownGame:");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
