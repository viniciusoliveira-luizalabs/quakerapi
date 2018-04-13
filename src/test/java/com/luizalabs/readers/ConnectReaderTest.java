package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.models.Date;
import com.luizalabs.models.Event;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class ConnectReaderTest {

	private GenericReader reader = new ConnectReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ClientConnect);
		
		row.setLine("20:38 ClientConnect: 2");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
