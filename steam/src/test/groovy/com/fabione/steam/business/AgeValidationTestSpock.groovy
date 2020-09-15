package com.fabione.steam.business

import org.springframework.boot.test.context.SpringBootTest

import com.fabione.steam.exception.PlayerInfoException
import com.fabione.steam.service.business.AgeValidatorServiceImpl
import com.fabione.steam.service.business.EmailValidatorServiceImpl
import com.fabione.steam.utils.TestUtilsForGroovy

import spock.lang.Specification;

@SpringBootTest
class AgeValidationTestSpock extends Specification{

	private AgeValidatorServiceImpl ageValidation

	def setup() {
		ageValidation = new AgeValidatorServiceImpl();
        TestUtilsForGroovy.setAgePropierties(ageValidation)
	}

	
	def "Should be a valid age"()
	{
		given:
		def age = 24
 
	when:
		ageValidation.AgeMinValidator(age)
 
	then:
		 noExceptionThrown()
	}
	
	def "Should be a invalid age"()
	{
		given:
		def age = 12
 
	when:
		ageValidation.AgeMinValidator(age)
 
	then:
		 thrown(PlayerInfoException)
	}
	
	
}
