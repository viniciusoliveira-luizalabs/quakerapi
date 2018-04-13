package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Date;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class ScoreReaderTest {

	private GenericReader reader = new ScoreReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.score);
		
		row.setLine("11:57 score: 20  ping: 4  client: 4 Zeh");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
