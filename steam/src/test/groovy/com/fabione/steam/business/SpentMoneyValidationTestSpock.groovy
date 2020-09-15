package com.fabione.steam.business

import org.springframework.boot.test.context.SpringBootTest

import com.fabione.steam.exception.WalletAmountException
import com.fabione.steam.service.business.SpentAmountValidatorServiceImpl

import spock.lang.Specification;
import spock.lang.Unroll

@SpringBootTest
class SpentMoneyValidationTestSpock extends Specification{

	private SpentAmountValidatorServiceImpl spentMoneyValidation

	def setup() {
		spentMoneyValidation = new SpentAmountValidatorServiceImpl()
	}
	
	def "Should return double value"()
	{
		given:
		def currentAmount = 24.0
		def gamePriceAmount = 11.0
 
	when:
		def result = spentMoneyValidation.spentAmountValidator(currentAmount, gamePriceAmount)
 
	then:
		result == 13.0
		noExceptionThrown()
	}
	
	@Unroll
	def "The current amount is #a, minus the game price #b, the result is #c"(def a
		,def b, def c) {
		
		expect:
		spentMoneyValidation.spentAmountValidator(a,b) == c

		where:
		a  |  b  | c
		25 | 15  | 10
		3  |  2  | 1
		25 | 10  | 15
	}
	
	def "Should throw an exception"()
	{
		given:
		def currentAmount = 24.0
		def gamePriceAmount = 40.0
 
	when:
		def result = spentMoneyValidation.spentAmountValidator(currentAmount, gamePriceAmount)
 
	then:
		 thrown(WalletAmountException)
	}
	
	def "Should throw an unexpected exception"()
	{
		given:
		def currentAmount = "hola"
		def gamePriceAmount = 40.0
 
	when:
		def result = spentMoneyValidation.spentAmountValidator(currentAmount, gamePriceAmount)
 
	then:
		 thrown(MissingMethodException)
	}
	
	
}
