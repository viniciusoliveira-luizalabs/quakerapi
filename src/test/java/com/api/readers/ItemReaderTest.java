package com.api.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.api.enums.Event;
import com.api.models.Date;
import com.api.models.Row;
import com.api.readers.GenericReader;
import com.api.readers.ItemReader;

public class ItemReaderTest {

	private GenericReader reader = new ItemReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.Item);
		
		row.setLine("20:40 Item: 2 weapon_rocketlauncher");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
