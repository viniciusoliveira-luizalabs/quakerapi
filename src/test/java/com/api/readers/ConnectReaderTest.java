package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.ConnectReader;
import com.api.readers.GenericReader;

public class ConnectReaderTest {

	private GenericReader reader = new ConnectReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ClientConnect);
		
		row.setLine("20:38 ClientConnect: 2");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
