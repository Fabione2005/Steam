package com.fabione.steam.service.game;

import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabione.steam.exception.GameInfoException;
import com.fabione.steam.exception.GameNotFoundException;
import com.fabione.steam.model.Game;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.PageInfoRequest;
import com.fabione.steam.model.generic.PageInfoResponse;
import com.fabione.steam.model.generic.PagingSortingInitParam;
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
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseGameWrapper(new LinkedHashSet<>(gameRepository.findAll())));
	}

	@Override
	public void setRepository(GameRepository repository) {
		this.gameRepository = repository;
	}

	@Override
	public ResponseEntity<ResponseGameWrapper> retrieveAllGamesOrderByPrice(boolean isAsc) {

		List<Game> games = isAsc ? gameRepository.findByOrderByPriceAsc() : gameRepository.findByOrderByPriceDesc();

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseGameWrapper(new LinkedHashSet<>(games)));
	}

	@Override
	public ResponseEntity<ResponseGameWrapper> retrieveAllGamesContainingNameLike(String name) {

		List<Game> games = gameRepository.findByNameIgnoreCaseContaining(name);

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseGameWrapper(new LinkedHashSet<>(games)));
	}

	@Override
	public ResponseEntity<ResponseGameWrapper> retrieveAllPaginated(PageInfoRequest pageInfo) {

		Page<Game> gamesInfo = gameRepository
				.findAll(PageRequest.of(pageInfo.getPageNumber(), pageInfo.getRowsByPage()));

		ResponseGameWrapper responseGames = new ResponseGameWrapper(new LinkedHashSet<>(gamesInfo.getContent()));

		PageInfoResponse pageInfoResponse = new PageInfoResponse();

		pageInfoResponse.setTotalNumberOfElements(gamesInfo.getTotalElements());
		pageInfoResponse.setTotalNumberOfPages(gamesInfo.getTotalPages());

		responseGames.setPageInfo(pageInfoResponse);

		return ResponseEntity.status(HttpStatus.FOUND).body(responseGames);
	}

	@Override
	public ResponseEntity<ResponseGameWrapper> retrieveAllGamesContainingNameLikePaginated(String name,
			PagingSortingInitParam initParam) {

		Sort sortCriteria = initParam.getOrderCriteria().isAsc() ?
				Sort.by(initParam.getOrderCriteria().getCriteriaParam()).ascending()
				:Sort.by(initParam.getOrderCriteria().getCriteriaParam()).descending();
		
		Page<Game> gamesInfo = gameRepository.findByNameIgnoreCaseContaining(name,
				PageRequest.of(initParam.getPageInfo().getPageNumber(), initParam.getPageInfo().getRowsByPage(),
						sortCriteria));

		ResponseGameWrapper responseGames = new ResponseGameWrapper(new LinkedHashSet<>(gamesInfo.getContent()));

		PageInfoResponse pageInfoResponse = new PageInfoResponse();

		pageInfoResponse.setTotalNumberOfElements(gamesInfo.getTotalElements());
		pageInfoResponse.setTotalNumberOfPages(gamesInfo.getTotalPages());
		
		responseGames.setPageInfo(pageInfoResponse);

		return ResponseEntity.status(HttpStatus.FOUND).body(responseGames);
	}

}
