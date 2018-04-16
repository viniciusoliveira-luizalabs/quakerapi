package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.GenericReader;
import com.api.readers.KillReader;

public class KillReaderTest {

	private GenericReader reader = new KillReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.Kill);
		
		row.setLine("20:54 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
