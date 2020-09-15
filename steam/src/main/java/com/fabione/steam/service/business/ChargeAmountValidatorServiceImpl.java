package com.fabione.steam.service.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fabione.steam.exception.WalletAmountException;

@Service
public class ChargeAmountValidatorServiceImpl implements ChargeAmountValidatorService{

	@Value("${max_charge_amount_allow}")
	private double maxAmountChargeAllow;
	
	@Override
	public double chargeAmountValidator(double currentAmount, double newAmount) {
		
		if(newAmount > maxAmountChargeAllow) 
		{
			throw new WalletAmountException("The amount to charge exceeds the max amount allow, the current max amount allowed is: "+maxAmountChargeAllow, HttpStatus.CONFLICT);
		}
		
		return currentAmount+newAmount;
	}

}
