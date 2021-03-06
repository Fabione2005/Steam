package com.fabione.steam.utils;

import org.springframework.test.util.ReflectionTestUtils;

import com.fabione.steam.model.Game;
import com.fabione.steam.model.Player;
import com.fabione.steam.model.Wallet;
import com.fabione.steam.service.business.AgeValidatorServiceImpl;
import com.fabione.steam.service.business.ChargeAmountValidatorServiceImpl;
import com.fabione.steam.service.business.EmailValidatorServiceImpl;

public class TestUtilsForGroovy {
	
	public static void setEmailPropierties(EmailValidatorServiceImpl emailValidation) {
		ReflectionTestUtils.setField(emailValidation, "regexEmail", "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z][cl])$");
        ReflectionTestUtils.setField(emailValidation, "emailMsg", "aaaaaaa@dominio.cl");
	}
	
	
	public static void setAgePropierties(AgeValidatorServiceImpl ageValidation) {
		ReflectionTestUtils.setField(ageValidation, "ageMin", 18);
        ReflectionTestUtils.setField(ageValidation, "ageMinMsg", "La edad ingresada debe ser mayor a");
	}
	
	public static void setChargeMoneyPropiertie(ChargeAmountValidatorServiceImpl chargeMoneyValidation) {
		ReflectionTestUtils.setField(chargeMoneyValidation, "maxAmountChargeAllow", 1000);
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
	
	public static Player getPlayer(String name, String lastName, String email, int age)
	{
		return new Player(name,lastName,email,age);
	}
	
	public static Wallet getWallet(double moneyAmount) 
	{
		return new Wallet(moneyAmount,null,null);
	}
	
	
}
