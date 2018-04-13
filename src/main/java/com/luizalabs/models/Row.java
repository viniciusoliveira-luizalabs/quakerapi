package com.luizalabs.models;

import java.io.Serializable;

import com.luizalabs.enums.Event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ivo
 * 
 * Objeto que guarda informações da linha do log
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Row implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String line = "";

	private Date time;

	private Event event;

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

	public String getLine() {
		return line.trim();
	}

}
