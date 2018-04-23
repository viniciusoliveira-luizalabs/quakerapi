package com.api.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ivo
 * 
 * Objeto que armazena informações da resposta em caso de exception
 */
@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime createdOn;
	private String message;
	private String details;
}
