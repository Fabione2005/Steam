package com.fabione.steam.exception;

import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends GenericException implements CommonException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6664020609555381727L;

	public PlayerNotFoundException(String message, HttpStatus status) {
		super(message, status);
	}
	
	public PlayerNotFoundException() 
	{
		super("Player was not found", HttpStatus.NOT_FOUND);
	}
	
}
