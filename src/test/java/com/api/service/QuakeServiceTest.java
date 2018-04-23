package com.api.service;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.api.config.GameSetup;
import com.api.model.Game;

import com.api.exception.EntityNotFoundException;

/**
 * @author ivofreitas
 *
 */
public class QuakeServiceTest {
	@Mock
	private GameSetup gameSetup;

	private QuakeService quakeService;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		quakeService = new QuakeService(gameSetup);
	}

	@Test
	public void getGames() {

		String[] lines = parseFile();

		Mockito.when(gameSetup.getLines()).thenReturn(lines);

		assertEquals(2, quakeService.getGames().size());

	}

	@Test
	public void getGame() {
		String[] lines = parseFile();

		Mockito.when(gameSetup.getLines()).thenReturn(lines);
		
		Map<String, Integer> kills = new TreeMap<>();
		kills.put("Isgalamido", 0);
		kills.put("Mocinha", 0);
		
		Game game = new Game();
		game.setTotalKills(2);
		game.setNumber(1);
		game.setPlayers(new Object[] {"Isgalamido", "Mocinha"});
		game.setKills(kills);
		
		assertEquals(game, quakeService.getGame(1));
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void getGameNull() {
		String[] lines = parseFile();
		
		Mockito.when(gameSetup.getLines()).thenReturn(lines);
		
		quakeService.getGame(10);
	}
	
	private String[] parseFile() {
		String[] lines = new String[25];

		lines[0] = "0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
		lines[1] = "15:00 Exit: Timelimit hit.";
		lines[2] = "20:34 ClientConnect: 2";
		lines[3] = "20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[4] = "20:37 ClientBegin: 3";
		lines[5] = "21:51 ClientUserinfoChanged: 3 n\\Dono da Bola\\t\\0\\model\\sarge/krusade\\hmodel\\sarge/krusade\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[6] = "21:53 ClientUserinfoChanged: 3 n\\Mocinha\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[7] = "22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH";
		lines[8] = "25:05 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT";
		lines[9] = "26:53 ClientUserinfoChanged: 4 n\\Mocinha\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[9] = "27:37 ShutdownGame:";
		lines[10] = "12:13 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\bot_minplayers\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";
		lines[11] = "12:14 ClientUserinfoChanged: 2 n\\Dono da Bola\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[12] = "12:14 ClientUserinfoChanged: 3 n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[13] = "12:14 ClientUserinfoChanged: 4 n\\Zeh\\t\\0\\model\\sarge/default\\hmodel\\sarge/default\\g_redteam\\\\g_blueteam\\\\c1\\1\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[14] = "12:14 ClientUserinfoChanged: 5 n\\Assasinu Credi\\t\\0\\model\\sarge\\hmodel\\sarge\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[15] = "12:24 Kill: 3 4 6: Isgalamido killed Zeh by MOD_ROCKET";
		lines[16] = "12:26 Kill: 3 2 7: Isgalamido killed Dono da Bola by MOD_ROCKET_SPLASH";
		lines[17] = "13:36 ClientUserinfoChanged: 2 n\\Zeh\\t\\0\\model\\sarge/default\\hmodel\\sarge/default\\g_redteam\\\\g_blueteam\\\\c1\\1\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[18] = "13:37 ClientUserinfoChanged: 2 n\\Zeh\\t\\0\\model\\sarge/default\\hmodel\\sarge/default\\g_redteam\\\\g_blueteam\\\\c1\\1\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
		lines[19] = "14:02 Kill: 1022 5 22: <world> killed Assasinu Credi by MOD_TRIGGER_HURT";
		lines[20] = "14:15 Kill: 2 5 10: Zeh killed Assasinu Credi by MOD_RAILGUN";
		lines[21] = "14:29 Kill: 5 5 7: Assasinu Credi killed Assasinu Credi by MOD_ROCKET_SPLASH";
		lines[22] = "14:38 Kill: 1022 5 22: <world> killed Assasinu Credi by MOD_TRIGGER_HURT";
		lines[23] = "14:46 Kill: 5 2 6: Assasinu Credi killed Zeh by MOD_ROCKET";
		lines[24] = "15:14 ClientUserinfoChanged: 9 n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
		return lines;
	}
}
