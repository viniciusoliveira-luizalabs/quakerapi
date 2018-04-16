package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.GenericReader;
import com.api.readers.ScoreReader;

public class ScoreReaderTest {

	private GenericReader reader = new ScoreReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.score);
		
		row.setLine("11:57 score: 20  ping: 4  client: 4 Zeh");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
