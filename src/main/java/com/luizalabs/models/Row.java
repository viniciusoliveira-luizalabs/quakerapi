package com.luizalabs.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ivo
 *
 */
@Getter
@Setter
@EqualsAndHashCode
public class Row {

	private String rawLine = "";

	private Date time;

	private final Event event;

	private String description;

	private String origin;

	private String target;

	public Row(Date time, Event event, String description, String origin, String target) {
		this(time, event);
		this.description = description;
		this.origin = origin;
		this.target = target;
	}

	public Row(Date time, Event event) {
		this.time = time;
		this.event = event;
	}

	public static Row blankEvent() {
		return new Row(new Date(), Event.BLANK);
	}

	public String getRawLine() {
		return rawLine.trim();
	}

}
