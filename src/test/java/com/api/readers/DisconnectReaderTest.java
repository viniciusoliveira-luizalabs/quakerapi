package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.DisconnectReader;
import com.api.readers.GenericReader;

public class DisconnectReaderTest {

	private GenericReader reader = new DisconnectReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ClientDisconnect);
		
		row.setLine("21:10 ClientDisconnect: 2");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
