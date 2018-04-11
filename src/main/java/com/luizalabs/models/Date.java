package com.luizalabs.models;

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
public class Date {

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
