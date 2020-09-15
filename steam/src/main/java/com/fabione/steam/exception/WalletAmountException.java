package com.fabione.steam.exception;

import org.springframework.http.HttpStatus;

public class WalletAmountException extends GenericException implements CommonException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6664020609555381727L;

	public WalletAmountException(String message, HttpStatus status) {
		super(message, status);
	}
	
}
