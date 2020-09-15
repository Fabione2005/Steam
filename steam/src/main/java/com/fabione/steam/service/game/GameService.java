package com.fabione.steam.service.game;

import org.springframework.http.ResponseEntity;

import com.fabione.steam.model.Game;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.ResponseGameWrapper;

public interface GameService {

	ResponseEntity<BaseResult> createGame(Game game);
	ResponseEntity<BaseResult> deleteGame(Long idGame);
	ResponseEntity<ResponseGameWrapper> retrieveAllGames();
	
}
