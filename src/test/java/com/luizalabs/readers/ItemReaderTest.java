package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.enums.Event;
import com.luizalabs.models.Date;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class ItemReaderTest {

	private GenericReader reader = new ItemReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.Item);
		
		row.setLine("20:40 Item: 2 weapon_rocketlauncher");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
