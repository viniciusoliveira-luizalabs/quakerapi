package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.models.Date;
import com.luizalabs.models.Event;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class SayReaderTest {

	private GenericReader reader = new SayReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.say);
		
		row.setLine("981:21 say: Oootsimo: team red");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
