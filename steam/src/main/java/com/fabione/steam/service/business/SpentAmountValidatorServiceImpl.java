package com.fabione.steam.service.business;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fabione.steam.exception.WalletAmountException;

@Service
public class SpentAmountValidatorServiceImpl implements SpentAmountValidatorService{

	@Override
	public double spentAmountValidator(double currentAmount,double gamePriceAmount) {
		
		if(currentAmount < gamePriceAmount) 
		{
			throw new WalletAmountException("The user doesn´t have enough money to buy this game, current money in wallet :"
					+currentAmount+" Price of the game : "+gamePriceAmount, HttpStatus.CONFLICT);
		}
		
		return currentAmount-gamePriceAmount;
	}

}
