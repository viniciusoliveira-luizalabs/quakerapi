package com.api.exception;

/**
 * @author Ivo
 *
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1366479614080419696L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}