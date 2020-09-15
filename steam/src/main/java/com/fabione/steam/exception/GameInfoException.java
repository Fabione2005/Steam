package com.fabione.steam.exception;

import org.springframework.http.HttpStatus;

public class GameInfoException extends GenericException implements CommonException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6664020609555381727L;

	public GameInfoException(String message, HttpStatus status) {
		super(message, status);
	}
	
	public GameInfoException() 
	{
		super("El juego ya existe", HttpStatus.CONFLICT);
	}
	
}
