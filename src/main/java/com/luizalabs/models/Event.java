package com.luizalabs.models;

/**
 * @author Ivo
 *
 */
public enum Event {

	BLANK,

	Item, Kill,

	InitGame, ShutdownGame, Exit,

	ClientConnect, ClientDisconnect, ClientUserinfoChanged, ClientBegin,

	score, say

}
