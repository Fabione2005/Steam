package com.fabione.steam.service.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fabione.steam.exception.PlayerInfoException;

@Service
public class EmailValidatorServiceImpl implements EmailValidatorService {

	@Value("${regex_email}")
	private String regexEmail;
	
	@Value("${email_format_message}")
	private String emailMsg;

	@Override
	public void validateEmail(String email) {

		boolean isValid = email.matches(regexEmail);

		if (!isValid) {
			throw new PlayerInfoException(emailMsg,HttpStatus.CONFLICT);
		}

	}

}
