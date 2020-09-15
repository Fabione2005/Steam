package com.fabione.steam.exception;

import org.springframework.http.HttpStatus;

public class GameNotFoundException extends GenericException implements CommonException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6664020609555381727L;

	public GameNotFoundException(String message, HttpStatus status) {
		super(message, status);
	}
	
	public GameNotFoundException() 
	{
		super("Game was not found", HttpStatus.NOT_FOUND);
	}
	
}
