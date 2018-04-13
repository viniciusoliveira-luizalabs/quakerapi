package com.luizalabs.readers;

import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public abstract class GenericReader {

    protected GenericReader successor;

    public void setSuccessor(GenericReader successor) {
        this.successor = successor;
    }

    abstract public Row processLine(Row row);
}
