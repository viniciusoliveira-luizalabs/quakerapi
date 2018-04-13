package com.luizalabs.models;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ivo
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Date implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int hours;
    private final int minutes;

    public Date() {
        this(0, 0);
    }

    public Date(int hours, int minutes) {
        this.hours = hours % 24;
        this.minutes = minutes % 60;
    }


}
