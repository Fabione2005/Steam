package com.fabione.steam.service.game;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabione.steam.exception.GameInfoException;
import com.fabione.steam.exception.GameNotFoundException;
import com.fabione.steam.model.Game;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.ResponseGameWrapper;
import com.fabione.steam.repository.GameRepository;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Override
	public ResponseEntity<BaseResult> createGame(Game game) {

		gameRepository.findByName(game.getName()).ifPresent(a -> {
			throw new GameInfoException("The Game already exists : " + game.getName(), HttpStatus.CONFLICT);
		});
		
		gameRepository.save(game);

		return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResult("game created"));
	}

	@Override
	public ResponseEntity<BaseResult> deleteGame(Long idGame) {

		Game gameToDelete = gameRepository.findById(idGame).orElseThrow(GameNotFoundException::new);

		gameRepository.delete(gameToDelete);

		return ResponseEntity.status(HttpStatus.OK).body(new BaseResult("Game deleted"));
	}

	@Override
	public ResponseEntity<ResponseGameWrapper> retrieveAllGames() {
		
		ResponseGameWrapper responseBody = new ResponseGameWrapper();

		responseBody.setGame(new HashSet<>(gameRepository.findAll()));

		return ResponseEntity.status(HttpStatus.FOUND).body(responseBody);
	}

	@Override
	public void setRepository(GameRepository repository) {
		this.gameRepository = repository;
	}

}
