package com.fabione.steam.exception;

import org.springframework.http.HttpStatus;

public class PlayerInfoException extends GenericException implements CommonException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6664020609555381727L;

	public PlayerInfoException(String message, HttpStatus status) {
		super(message, status);
	}
	
	public PlayerInfoException() 
	{
		super("El correo ya esta registrado", HttpStatus.CONFLICT);
	}
	
}
