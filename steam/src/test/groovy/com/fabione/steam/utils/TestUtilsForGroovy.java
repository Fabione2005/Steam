package com.fabione.steam.utils;

import org.springframework.test.util.ReflectionTestUtils;

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
	
	
	
}
