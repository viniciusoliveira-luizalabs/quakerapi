package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.BeginReader;
import com.api.readers.GenericReader;

public class BeginReaderTest {

	private GenericReader reader = new BeginReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ClientBegin);
		
		row.setLine("20:37 ClientBegin: 2");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
