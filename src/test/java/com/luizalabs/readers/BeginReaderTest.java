package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.models.Date;
import com.luizalabs.models.Event;
import com.luizalabs.models.Row;
import com.luizalabs.readers.BeginReader;
import com.luizalabs.readers.GenericReader;

public class BeginReaderTest {

	private GenericReader reader = new BeginReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ClientBegin);
		
		row.setLine("20:37 ClientBegin: 2");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
