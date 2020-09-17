package com.fabione.steam.utils;

import org.springframework.test.util.ReflectionTestUtils;

import com.fabione.steam.model.Game;
import com.fabione.steam.service.business.EmailValidatorServiceImpl;

public class TestUtils {
	
	
	public static void setEmailPropierties(EmailValidatorServiceImpl emailValidation) {
		ReflectionTestUtils.setField(emailValidation, "regexEmail", "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z][cl])$");
        ReflectionTestUtils.setField(emailValidation, "emailMsg", "aaaaaaa@dominio.cl");
	}
	
	public static Game getGame(Long id, String name, double weight, double price) 
	{
		Game game = new Game(name,weight,price);
		game.setId(id);
		
		return game;
	}
	
	public static Game getGame(String name, double weight, double price) 
	{
		return getGame(1L,name,weight,price);
	}
}
