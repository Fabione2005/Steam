package com.fabione.steam.service

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

import com.fabione.steam.exception.PlayerInfoException
import com.fabione.steam.exception.WalletAmountException
import com.fabione.steam.model.Game
import com.fabione.steam.model.Player
import com.fabione.steam.model.Wallet
import com.fabione.steam.repository.GameRepository
import com.fabione.steam.repository.PlayerRepository
import com.fabione.steam.service.game.GameService
import com.fabione.steam.service.player.PlayerService
import com.fabione.steam.utils.TestUtilsForGroovy

import spock.lang.Specification

@SpringBootTest(webEnvironment = NONE)
class PlayerServiceTestSpock extends Specification{

	@Autowired
	PlayerService playerService

	@Autowired
	GameService gameService

	PlayerRepository playerRepository = Mock()

	GameRepository gameRepository = Mock()

	def setup() {
		playerService.setPlayerRepository(playerRepository)
		gameService.setRepository(gameRepository)
	}

	def "Creating player successfully"() {

		setup:

		Player user = TestUtilsForGroovy.getPlayer("Robert","Baratheon","robertito@gmail.cl",25);

		playerRepository.findByEmail(user.getEmail()) >> Optional.empty()

		def message = "player created"
		def code = HttpStatus.CREATED

		when:
		def response = playerService.createPlayer(user)

		then:
		response.body.message == message
		response.status == code
		1 * playerRepository.save(user)
	}

	def "Creating player throws exception for min age constraint"() {

		setup:

		Player user = TestUtilsForGroovy.getPlayer("Robert","Baratheon","robertito@gmail.cl",11);

		playerRepository.findByEmail(user.getEmail()) >> Optional.empty()

		def message = "player created"
		def code = HttpStatus.CREATED

		when:
		def response = playerService.createPlayer(user)

		then:
		thrown(PlayerInfoException)
		0 * playerRepository.save(user)
	}

	def "Charge money to player successfully"() {

		setup:

		Player user = TestUtilsForGroovy.getPlayer("Robert","Baratheon","robertito@gmail.cl",25);

		playerRepository.findById(user.getId()) >> Optional.of(user)

		def code = HttpStatus.OK

		when:
		def response = playerService.chargeMoney(user.getId(),340.2)

		then:
		noExceptionThrown()
		response.status == code
		1 * playerRepository.save(user)
	}

	def "Charge money throws an exception for max money amount to charge exceeded"() {

		setup:

		def moneyAmont = 235646646

		Player user = TestUtilsForGroovy.getPlayer("Robert","Baratheon","robertito@gmail.cl",25);

		playerRepository.findById(user.getId()) >> Optional.of(user)

		when:
		playerService.chargeMoney(user.getId(),moneyAmont)

		then:
		thrown(WalletAmountException)
		0 * playerRepository.save(user)
	}

	def "Buy game to player successfully"() {

		setup:

		def moneyAmount = 200

		Game game = TestUtilsForGroovy.getGame("Zelda", 40, 66.3)

		gameRepository.findById(game.getId()) >> Optional.of(game)

		Player player = TestUtilsForGroovy.getPlayer("Robert","Baratheon","robertito@gmail.cl",25);

		Wallet wallet = TestUtilsForGroovy.getWallet(moneyAmount)

		player.wallet = wallet

		playerRepository.findById(player.getId()) >> Optional.of(player)

		def responseCodeExpected = HttpStatus.OK

		when:
		def response = playerService.buyGameToPlayer(player.getId(),game.getId())

		then:
		noExceptionThrown()
		response.status == responseCodeExpected
		1 * playerRepository.save(player)
	}

	def "Buy game to player throws error for not enough money to buy game"() {

		setup:

		def moneyAmount = 20

		Game game = TestUtilsForGroovy.getGame("Zelda", 40, 66.3)

		gameRepository.findById(game.getId()) >> Optional.of(game)

		Player player = TestUtilsForGroovy.getPlayer("Robert","Baratheon","robertito@gmail.cl",25);

		Wallet wallet = TestUtilsForGroovy.getWallet(moneyAmount)

		player.wallet = wallet

		playerRepository.findById(player.getId()) >> Optional.of(player)

		when:
		def response = playerService.buyGameToPlayer(player.getId(),game.getId())

		then:
		thrown(WalletAmountException)
		0 * playerRepository.save(player)
	}
}
