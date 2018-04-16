package com.api.enums;

/**
 * @author Ivo
 *
 * Lista de eventos que acontecem no jogo
 *
 */
public enum Event {

	BLANK,

	Item, Kill,

	InitGame, ShutdownGame, Exit,

	ClientConnect, ClientDisconnect, ClientUserinfoChanged, ClientBegin,

	score, say

}
