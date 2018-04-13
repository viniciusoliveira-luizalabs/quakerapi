package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.models.Date;
import com.luizalabs.models.Event;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class KillReaderTest {

	private GenericReader reader = new KillReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.Kill);
		
		row.setLine("20:54 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
