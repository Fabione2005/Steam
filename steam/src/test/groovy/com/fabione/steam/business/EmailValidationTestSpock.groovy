package com.fabione.steam.business

import org.springframework.boot.test.context.SpringBootTest

import com.fabione.steam.exception.PlayerInfoException
import com.fabione.steam.service.EmailValidatorServiceImpl
import com.fabione.steam.utils.TestUtilsForGroovy

import spock.lang.Specification;

@SpringBootTest
class EmailValidationTestSpock extends Specification{

	private EmailValidatorServiceImpl emailValidation

	def setup() {
		emailValidation = new EmailValidatorServiceImpl();
        TestUtilsForGroovy.setEmailPropierties(emailValidation)
	}

	
	def "Should be a valid email"()
	{
		given:
		def email = "Test@gmail.cl"
 
	when:
		emailValidation.validateEmail(email)
 
	then:
		 noExceptionThrown()
	}
	
	def "Should be a invalid email"()
	{
		given:
		def email = "Testtttgmail.cl"
 
	when:
		emailValidation.validateEmail(email)
 
	then:
		 thrown(PlayerInfoException)
	}
	
	
}
