package com.fabione.steam.utils;

import org.springframework.test.util.ReflectionTestUtils;

import com.fabione.steam.service.business.EmailValidatorServiceImpl;

public class TestUtils {
	
	
	public static void setEmailPropierties(EmailValidatorServiceImpl emailValidation) {
		ReflectionTestUtils.setField(emailValidation, "regexEmail", "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z][cl])$");
        ReflectionTestUtils.setField(emailValidation, "emailMsg", "aaaaaaa@dominio.cl");
	}
	
	
}
