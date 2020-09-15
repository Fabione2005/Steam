package com.fabione.steam.service.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fabione.steam.exception.PlayerInfoException;

@Service
public class AgeValidatorServiceImpl implements AgeValidatorService{

	@Value("${age_min}")
	private int ageMin;
	
	@Value("${age_min_msg}")
	private String ageMinMsg;
	
	@Override
	public void AgeMinValidator(int age) {
		
		boolean isValid = age >= ageMin;

		if (!isValid) {
			throw new PlayerInfoException(ageMinMsg+ageMin,HttpStatus.CONFLICT);
		}
		
	}
	
	

}
