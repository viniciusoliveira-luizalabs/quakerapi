package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.models.Date;
import com.luizalabs.models.Event;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class DisconnectReaderTest {

	private GenericReader reader = new DisconnectReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ClientDisconnect);
		
		row.setLine("21:10 ClientDisconnect: 2");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
