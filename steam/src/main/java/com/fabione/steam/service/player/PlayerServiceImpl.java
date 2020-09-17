package com.fabione.steam.service.player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabione.steam.exception.GameNotFoundException;
import com.fabione.steam.exception.PlayerInfoException;
import com.fabione.steam.exception.PlayerNotFoundException;
import com.fabione.steam.model.Game;
import com.fabione.steam.model.Player;
import com.fabione.steam.model.Wallet;
import com.fabione.steam.model.generic.BaseResult;
import com.fabione.steam.model.generic.ResponsePlayerWrapper;
import com.fabione.steam.repository.GameRepository;
import com.fabione.steam.repository.PlayerRepository;
import com.fabione.steam.service.business.AgeValidatorService;
import com.fabione.steam.service.business.ChargeAmountValidatorService;
import com.fabione.steam.service.business.EmailValidatorService;
import com.fabione.steam.service.business.SpentAmountValidatorService;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository repository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private EmailValidatorService emailValidator;

	@Autowired
	private AgeValidatorService ageValidator;

	@Autowired
	private ChargeAmountValidatorService chargeAmountValidator;

	@Autowired
	private SpentAmountValidatorService spentAmountValidator;

	@Override
	public ResponseEntity<BaseResult> createPlayer(Player user) {

		repository.findByEmail(user.getEmail()).ifPresent(player ->{throw new PlayerInfoException("Email already registered in the system",HttpStatus.CONFLICT);});
		
		emailValidator.validateEmail(user.getEmail());

		ageValidator.validateAge(user.getAge());

		user.setCreated(LocalDateTime.now());

		repository.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResult("player created"));
	}

	@Override
	public ResponseEntity<ResponsePlayerWrapper> retrieveAllPlayers() {

		ResponsePlayerWrapper responseBody = new ResponsePlayerWrapper();

		responseBody.setPlayer(new HashSet<>(repository.findAll()));

		return ResponseEntity.status(HttpStatus.FOUND).body(responseBody);
	}

	@Override
	public ResponseEntity<BaseResult> deletePlayer(Long playerId) {

		Player playerFound = getPlayerFound(playerId);

		repository.delete(playerFound);

		return ResponseEntity.status(HttpStatus.OK).body(new BaseResult("player deleted"));
	}

	@Override
	public ResponseEntity<ResponsePlayerWrapper> retrievePlayersOlderThan(int age) {

		ResponsePlayerWrapper responseBody = new ResponsePlayerWrapper();

		responseBody.setPlayer(new HashSet<>(repository.getPlayerOrderThanProcedure(age)));

		return ResponseEntity.status(HttpStatus.FOUND).body(responseBody);
	}

	@Override
	public ResponseEntity<BaseResult> buyGameToPlayer(Long playerId, Long gameId) {

		Game gameFound = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);

		Player playerFound = getPlayerFound(playerId);
		
		boolean hasGame = playerFound.getGames().stream().anyMatch(games -> games.getId() == gameFound.getId());
		
		if(hasGame) 
		{
			throw new PlayerInfoException("The game "+gameFound.getName()+" user "+playerFound.getName()+" already has it in the library",HttpStatus.CONFLICT);
		}
		
		Wallet wallet = getWallet(playerFound);

		wallet.setMoneyAmount(spentAmountValidator.spentAmountValidator(wallet.getMoneyAmount(), gameFound.getPrice()));
		wallet.setLastTimeMoneySpent(LocalDateTime.now());
		
		playerFound.setWallet(wallet);

		List<Game> tempList = new ArrayList<>(playerFound.getGames());
		tempList.add(gameFound);
		
		playerFound.setGames(new HashSet<>(tempList));

		repository.save(playerFound);

		return ResponseEntity.status(HttpStatus.OK).body(
				new BaseResult("Game " + gameFound.getName() + " bought succesfully by user " + playerFound.getName()));
	}

	@Override
	public ResponseEntity<BaseResult> chargeMoney(Long playerId, double moneyAmount) {

		Player playerFound = getPlayerFound(playerId);
		
		Wallet wallet = getWallet(playerFound);

		wallet.setMoneyAmount(chargeAmountValidator.chargeAmountValidator(wallet.getMoneyAmount(), moneyAmount));
		wallet.setLastTimeMoneyCharged(LocalDateTime.now());

		playerFound.setWallet(wallet);

		repository.save(playerFound);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new BaseResult("User " + playerFound.getName() + " has successfully charged " + moneyAmount
						+ " to the wallet, current amount in wallet is: " + wallet.getMoneyAmount()));
	}

	@Override
	public void setPlayerRepository(PlayerRepository repository) {
		this.repository = repository;
	}
	
	private Wallet getWallet(Player playerFound) {
		Wallet wallet = playerFound.getWallet() != null ? playerFound.getWallet() : new Wallet();
		return wallet;
	}

	private Player getPlayerFound(Long playerId) {
		Player playerFound = repository.findById(playerId).orElseThrow(PlayerNotFoundException::new);
		return playerFound;
	}


}
