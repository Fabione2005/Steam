package com.fabione.steam.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabione.steam.exception.GameInfoException;
import com.fabione.steam.model.Game;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.ResponseGameWrapper;
import com.fabione.steam.repository.GameRepository;
import com.fabione.steam.service.game.GameService;
import com.fabione.steam.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceMockTest {

	@Autowired
	private GameService service;

	@MockBean
	private GameRepository repository;
	
	@Test
	public void getAllGamesTest() {
		String name = "Zelda";
		
		when(repository.findAll()).thenReturn(Stream
				.of(TestUtils.getGame(name, 40, 66.3)).collect(Collectors.toList()));
		
		ResponseGameWrapper responseBody = service.retrieveAllGames().getBody();
		
		assertEquals(1,responseBody.getGame().size());
		
		String nameResponse = responseBody.getGame().stream().findFirst().map(Game::getName)
				.get();
		
		assertEquals(name, nameResponse);
	}
	
	@Test
	public void createGameSuccessfully() {
		
		String gameMsg = "game created";
		
		Game game = TestUtils.getGame("Metroid", 44.2, 23.2);
		
		when(repository.findByName(game.getName())).thenReturn(Optional.empty());
		
		BaseResult responseBody = service.createGame(game).getBody();
		
		assertEquals(gameMsg,responseBody.getMessage());
		
		verify(repository, times(1)).save(game);
	}
	
	@Test(expected = GameInfoException.class)
	public void createGameThrowsException() {
		
		Game game = TestUtils.getGame("Metroid", 44.2, 23.2);
		
		when(repository.findByName(game.getName())).thenReturn(Optional.of(game));
		
		service.createGame(game);
		
		verify(repository, times(0)).save(game);
	}
	
}
