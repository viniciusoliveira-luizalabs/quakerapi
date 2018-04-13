package com.luizalabs.readers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.luizalabs.models.Date;
import com.luizalabs.models.Event;
import com.luizalabs.models.Row;
import com.luizalabs.readers.GenericReader;

public class InfoChangedReaderTest {

	private GenericReader reader = new InfoChangedReader();
	
	@Test
	public void processLine() {
		
		Row row = new Row(new Date(), Event.ClientUserinfoChanged);
		
		row.setLine("20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");
		
		assertEquals(row, reader.processLine(row));
		
	}
	
}
