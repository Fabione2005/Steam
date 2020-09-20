package com.fabione.steam.service.game;

import org.springframework.http.ResponseEntity;

import com.fabione.steam.model.Game;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.PageInfoRequest;
import com.fabione.steam.model.generic.PagingSortingInitParam;
import com.fabione.steam.model.generic.ResponseGameWrapper;
import com.fabione.steam.repository.GameRepository;

public interface GameService {

	ResponseEntity<BaseResult> createGame(Game game);
	ResponseEntity<BaseResult> deleteGame(Long idGame);
	ResponseEntity<ResponseGameWrapper> retrieveAllGames();
	ResponseEntity<ResponseGameWrapper> retrieveAllGamesOrderByPrice(boolean isAsc);
	ResponseEntity<ResponseGameWrapper> retrieveAllGamesContainingNameLike(String name);
	ResponseEntity<ResponseGameWrapper> retrieveAllGamesContainingNameLikePaginated(String name,PagingSortingInitParam initParam);
	ResponseEntity<ResponseGameWrapper> retrieveAllPaginated(PageInfoRequest pageInfo);
	void setRepository(GameRepository repository);
	
}
