package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.GenericReader;
import com.api.readers.SayReader;

public class SayReaderTest {

	private GenericReader reader = new SayReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.say);
		
		row.setLine("981:21 say: Oootsimo: team red");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
