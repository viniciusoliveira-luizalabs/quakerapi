package com.luizalabs.parsers;

import com.luizalabs.models.Row;

/**
 * @author Ivo
 *
 */
public abstract class AbstractLineParser {

    protected AbstractLineParser successor;

    public void setSuccessor(AbstractLineParser successor) {
        this.successor = successor;
    }

    abstract public Row processLine(Row row);
}
