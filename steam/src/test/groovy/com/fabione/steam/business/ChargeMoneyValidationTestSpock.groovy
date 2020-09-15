package com.fabione.steam.business

import org.springframework.boot.test.context.SpringBootTest

import com.fabione.steam.exception.WalletAmountException
import com.fabione.steam.service.business.ChargeAmountValidatorServiceImpl
import com.fabione.steam.utils.TestUtilsForGroovy

import spock.lang.Specification;
import spock.lang.Unroll

@SpringBootTest
class ChargeMoneyValidationTestSpock extends Specification{

	private ChargeAmountValidatorServiceImpl chargeMoneyValidation

	def setup() {
		chargeMoneyValidation = new ChargeAmountValidatorServiceImpl()
		TestUtilsForGroovy.setChargeMoneyPropiertie(chargeMoneyValidation)
	}

	def "Should return double value"() {
		given:
		def currentAmount = 24.0
		def newAmount = 11.0

		when:
		def result = chargeMoneyValidation.chargeAmountValidator(currentAmount,newAmount);

		then:
		result == 35.0
		noExceptionThrown()
	}

	@Unroll
	def "The current amount is #currentAmount plus the new amount #newAmount the result is #result"(def currentAmount
		,def newAmount, def result) {
		
		expect:
		chargeMoneyValidation.chargeAmountValidator(currentAmount,newAmount) == result

		where:
		currentAmount  | newAmount | result
		    25 		   |    15     | 40
		     3  	   |     2     | 5
		    25 		   |    10 	   | 35
	}

	def "Should throw an exception"() {
		given:
		def currentAmount = 24.0
		def newAmount = 2000

		when:
		def result = chargeMoneyValidation.chargeAmountValidator(currentAmount, newAmount)

		then:
		thrown(WalletAmountException)
	}

	def "Should throw an unexpected exception"() {
		given:
		def currentAmount = "hola"
		def gamePriceAmount = 40.0

		when:
		def result = chargeMoneyValidation.chargeAmountValidator(currentAmount, gamePriceAmount)

		then:
		thrown(MissingMethodException)
	}
}
