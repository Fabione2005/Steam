package com.fabione.steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabione.steam.model.Game;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.PageInfoRequest;
import com.fabione.steam.model.generic.PagingSortingInitParam;
import com.fabione.steam.model.generic.ResponseGameWrapper;
import com.fabione.steam.service.game.GameService;

@RestController
public class GameController {

	@Autowired
	GameService service;
	
	@GetMapping(value="games",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseGameWrapper> retriveGames() {
		return service.retrieveAllGames();
	}
	
	@PostMapping(value="game",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResult> saveUsers(@RequestBody Game game) {		
		return service.createGame(game);
	}
	
	@DeleteMapping(value = "/game/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResult> deleteGame(@PathVariable Long id) {
		return service.deleteGame(id);
	}
	
	@GetMapping(value="games/price/{isAsc}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseGameWrapper> retriveGamesByOrderPrice(@PathVariable boolean isAsc) {
		return service.retrieveAllGamesOrderByPrice(isAsc);
	}
	
	@GetMapping(value="games/name/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseGameWrapper> retriveGamesByOrderPrice(@PathVariable String name) {
		return service.retrieveAllGamesContainingNameLike(name);
	}
	
	@GetMapping(value="games/paginated",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseGameWrapper> retriveGamesPaginated(@RequestBody PageInfoRequest pageInfo) {
		return service.retrieveAllPaginated(pageInfo);
	}
	
	@GetMapping(value="games/{name}/paginated",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseGameWrapper> retriveGamesPaginated(@PathVariable String name,@RequestBody PagingSortingInitParam initParam) {
		return service.retrieveAllGamesContainingNameLikePaginated(name, initParam);
	}
	
}
