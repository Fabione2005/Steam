package com.fabione.steam.service

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

import com.fabione.steam.exception.GameInfoException
import com.fabione.steam.exception.GameNotFoundException
import com.fabione.steam.model.Game
import com.fabione.steam.repository.GameRepository
import com.fabione.steam.service.game.GameService
import com.fabione.steam.utils.TestUtilsForGroovy

import spock.lang.Specification

@SpringBootTest(webEnvironment = NONE)
class GameServiceTestSpock extends Specification{

	@Autowired
	GameService service

	GameRepository repository = Mock()

	def setup() {
		service.setRepository(repository)
	}

	def "Creating game successfully"() {

		setup:

		def game = TestUtilsForGroovy.getGame("Zelda", 40, 66.3)

		repository.findByName(game.name) >> Optional.empty()

		def createdMsg = "game created"

		when:
		def response = service.createGame(game)

		then:
		noExceptionThrown()
		response.body.message == createdMsg
		1 * repository.save(game)
	}

	def "Throwing exception when trying to create game that is already in data base"() {

		setup:

		def game = TestUtilsForGroovy.getGame("FIFA 20", 70.3, 66.3)

		repository.findByName(game.name) >> Optional.of(game)

		when:
		service.createGame(game)

		then:
		thrown(GameInfoException)
		0 * repository.save(game)
	}

	def "Deleting game successfully"() {

		setup:

		def game = TestUtilsForGroovy.getGame(23432L,"Super Mario 64", 6, 20)

		repository.findById(game.id) >> Optional.of(game)

		def responseExpected = HttpStatus.OK

		when:
		def response = service.deleteGame(game.id)

		then:
		noExceptionThrown()
		response.statusCode == responseExpected
		1 * repository.delete(game)
	}

	def "Throwing exception when trying to delete game that isn´t in data base"() {

		setup:

		def game = TestUtilsForGroovy.getGame(3453L,"Fall Guys", 6, 20)

		repository.findById(game.id) >> Optional.empty()

		when:
		def response = service.deleteGame(game.id)

		then:
		thrown(GameNotFoundException)
		0 * repository.delete(game)
	}

	def "Retrieving games successfully"() {

		setup:

		def mockGames = new Game[3]
		mockGames[0] = TestUtilsForGroovy.getGame(23432L,"Super Mario 64", 6, 20)
		mockGames[1] = TestUtilsForGroovy.getGame(23432L,"Zelda", 45, 60)
		mockGames[2] = TestUtilsForGroovy.getGame(23432L,"Metroid", 32, 45)

		repository.findAll() >> mockGames
		
		def responseExpected = HttpStatus.FOUND

		when:
		def response = service.retrieveAllGames()

		then:
		noExceptionThrown()
		response.statusCode == responseExpected
		response.body.game.size() == 3
	}
}
