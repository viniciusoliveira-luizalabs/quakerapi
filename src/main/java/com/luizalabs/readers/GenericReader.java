package com.luizalabs.readers;

import com.luizalabs.models.Row;

/**
 * @author Ivo
 * 
 *         Leitor que se baseia na linha lida no log para armazenar informações
 *
 */
public abstract class GenericReader {

	protected GenericReader successor;

	public void setSuccessor(GenericReader successor) {
		this.successor = successor;
	}

	abstract public Row processLine(Row row);
}
