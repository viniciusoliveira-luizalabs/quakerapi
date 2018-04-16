package com.api.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.api.models.Row;
import com.api.service.GameService;
import com.api.service.ProcessFile;

public class GameServiceTest {
	
	@Mock
	private ProcessFile log;
		
	private GameService gameService;
	
	@Before
	public void setup() {
	
		MockitoAnnotations.initMocks(this);
		
		gameService = new GameService(log);
	}
	
	@Test
	public void getGameList() {
				
		List<String> lines = new ArrayList<>();
		
		lines.add("0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0");
		lines.add("15:00 Exit: Timelimit hit.");
		lines.add("20:34 ClientConnect: 2");
		lines.add("20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");
		lines.add("20:37 ClientBegin: 2");
		lines.add("20:37 ShutdownGame:");		
				
		Mockito.when(log.iterator()).thenReturn(lines.iterator());
		
		assertEquals(1, gameService.getGameList().size());
		
	}

}
